# dataReport —— 跨境电商 VAT 计算与报告系统

面向跨境电商卖家的**增值税（VAT）一站式计算与报告工具**：上传平台订单 Excel → 自动汇总销售额 → 按申报国家与税率计算应缴 VAT → 生成带水印的《增值税报告》PDF 供下载留存。

> 一句话：**上传订单 → 自动计税 → 出具正式报告**

## ✨ 功能特性

| 模块              | 说明                                                                                                       |
| ----------------- | ---------------------------------------------------------------------------------------------------------- |
| 👤 用户注册/登录  | 用户名唯一校验、MD5 密码、记住我、登录次数统计；注册后需管理员审核（pass_flag）                            |
| ✅ 用户审核       | 管理员后台审核/驳回卖家，审核通过方可使用核心功能                                                          |
| 📥 订单导入       | 支持 **Amazon / GB / eBay** 三类平台订单 Excel，异步解析入库，生成导入批次，导入前清空该用户旧数据         |
| 📅 活动周期管理   | 导入时自动提取订单"业务活动周期"（如 `2018-JAN` → `201801`），支持多周期批量计算                           |
| 🌍 申报国家配置   | 从征税国家名单（tax_no_country）中勾选申报国家，保存用户配置                                               |
| 🧮 VAT 自动计算   | 按「用户 + 周期 + 申报国家」执行；A/B/C 三种计税类型汇总 → 多币种汇率折算 → 按税率（标准/低/初始）计算税金 |
| 📄 PDF 增值税报告 | 生成 A4 报告（报告号、计算日期、用户账号、明细表、水印、中文字体），记录入库、支持下载                     |
| 📋 订单查询       | 分页查看已导入订单                                                                                         |
| ⚙️ 计税参数配置   | 用户选择计税方式（A/B 分型 或 发货国交税模式）                                                             |
| 🛡️ 验证码         | 登录图形验证码 + 管理员注册短信验证码（阿里云短信）                                                        |
| 🔐 管理后台       | 用户列表、用户审核、角色分配、系统级配置（system_config）                                                  |
| 📱 微信（预留）   | 官网二维码（已完成）；微信支付统一下单（半成品，未完成）                                                   |

## 🛠 技术栈

| 类别        | 技术                                                                          |
| ----------- | ----------------------------------------------------------------------------- |
| 框架        | Spring Boot 1.5.6 · MyBatis + PageHelper · Apache Shiro 1.2.5 · Druid         |
| 语言/运行时 | Java 1.7+，Servlet 3.1（内置 Tomcat）                                         |
| 数据库      | MySQL（库名 `tax_computing`）                                                 |
| 视图层      | FreeMarker + JSP + JSTL + jQuery / jQuery Validate                            |
| PDF         | iText（lowagie）                                                              |
| 其他        | ZXing（二维码）、阿里云短信 SDK、微信支付 SDK（wxpay-sdk-3.0.9.jar）、ehcache |

## 📁 目录结构

```
dataReport/
├── vat_computing/            # ★ 主项目（完整版：微信、Shiro、管理后台）
├── vat_computing_test/       # 旧版/精简版（无微信、Shiro、系统配置模块）
├── doc/                      # 项目文档（代码分析、原始需求、SRS）
├── cert/ · mykeys.jks        # SSL 证书（生产 HTTPS 备用）
└── wxpay-sdk-3.0.9.jar       # 微信支付 SDK
```

## 🚀 快速开始

### 环境要求

- JDK 1.7+（建议 1.8）
- Maven 3.x
- MySQL 5.x

### 构建

```bash
# 进入主项目
cd vat_computing

# 打包（默认 pro 环境；可用 -P dev / -P test 切换）
mvn clean package
```

### 配置

1. 创建数据库：`tax_computing`（表结构见 `doc/代码分析.md` 第 7 节）。
2. 修改 `src/main/resources/application-{dev|test|pro}.properties`：
   - `spring.datasource.*`：数据库连接（⚠️ 当前配置含明文密码，**建议改用环境变量注入**）
   - `upload.UPLOAD_ROOT` / `upload.DOWNLOAD_ROOT`：上传/下载根目录
   - `server.port`：端口（默认 8080），访问路径含 context-path `/v`
3. 微信/短信配置：`resources/weixin.properties`（appId、mch_id 等占位符，按需填写）。

### 运行

```bash
mvn spring-boot:run -P pro
# 或部署 war：将 target/vat-computing.war 放入外部 Tomcat webapps/
```

访问：`http://localhost:8080/v/`（前台）· `/v/system/`（管理后台）

## 🔄 核心业务流程

```
用户注册 → 管理员审核 → 登录（Shiro）
        → 上传订单 Excel（异步导入，先清旧数据，生成批次）
        → 勾选申报国家 + 选择活动周期 + 计税方式
        → VAT 计算（A/B/C 分型汇总 → 汇率折算 → 税率计税）
        → 生成《增值税报告》PDF → 下载留存
```

### VAT 计算规则要点

- **计税类型**：
  - **A 型**：发货国 ∈ 征税国家名单
  - **B 型**：到达国 ∉ 用户勾选国家 且 ∈ 征税国家名单
  - **C 型**：到达国 ∈ 征税国家名单（`computingMethod ≠ "1"`，发货国交税模式）
- **汇率**：`exchange_rate` 按周期存 from/to 币种汇率；目标国=from 取 `1/rate`，=to 取 `rate`
- **税金**：
  - 标准税率（价内税）：`税金 = 含税销售额 / (1 + 税率) × 税率`
  - 非标准税率（GB 初始/低税率）：`税金 = 含税销售额 × 税率`

## 📚 文档索引（doc/）

| 文档              | 说明                                                |
| ----------------- | --------------------------------------------------- |
| `doc/代码分析.md` | 代码结构、模块、数据库表、遗留问题全量分析          |
| `doc/原始需求.md` | 业务视角需求（背景痛点、功能清单、业务规则）        |
| `doc/需求文档.md` | 软件需求规格说明书（SRS：FR/BR/数据/接口/验收标准） |

## ⚠️ 已知问题 / 遗留项

1. **微信支付未完成**：统一下单、支付回调为半成品，仅官网二维码可用。
2. **安全隐患**：`application-dev.properties` 含数据库明文密码且已入公开仓库，需轮换并改为环境变量注入；Shiro 版本较旧（1.2.5）。
3. **代码质量问题**：Java 1.7 语法、大量 raw type Map 传参；上传目录硬编码 `E:/vat_computing/...`。
4. **冗余文件**：根目录 `OrderAmazonMapper.xml` 与 resources 下重复；`vat_computing_test` 为旧版子项目。

## 📄 License

本项目基于 **[Apache License 2.0](LICENSE)** 开源。

```
Copyright 2026 yuezu1026

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
```

- 📄 许可证全文见根目录 [`LICENSE`](LICENSE)
- 📄 版权与声明见 [`NOTICE`](NOTICE)

## 👨‍💻 作者

- GitHub: [yuezu1026](https://github.com/yuezu1026)
- Email: [yuezu1026@163.com](mailto:yuezu1026@163.com)
