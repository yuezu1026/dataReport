# License Header 添加任务记录

## 状态

✅ 已完成（2026-08，提交 979d7b9）

## 需求

- 对现有代码文件统一添加 Apache 2.0 license header，表明开源身份
- 前提：项目已开源为 Apache 2.0（LICENSE/NOTICE/pom 许可证声明，提交 0353a75）

## 设计决策

- **只处理项目自有代码**，严禁改动第三方库（jquery/bootstrap/dataTables/morris/raphael/easypiechart/chart.js/font-awesome 等，版权归原作者）
- 覆盖范围：
  - Java：`vat_computing` 与 `vat_computing_test` 全部 204 个源文件（`/* */` 块注释，插在 package 前）
  - XML：两个项目的 mapper/_.xml + pom.xml + pom2.xml + ehcache.xml + WEB-INF/_.xml（`<!-- -->` 注释，插在 `<?xml?>` 声明后）
  - properties：application-\*.properties、weixin.properties、log4j.properties（`#` 注释）
  - html/jsp：两个项目 templates/ 与 webapp 下自有页面（`<!-- -->` 注释）
  - 自有 js/css：`webapp/js/alert/`、`webapp/assets/js/self/dialog.js`、`css/main.css`、`css/style.css`
- 排除：webapp/assets/js 下全部第三方库、webapp/assets/css、font-awesome、jquery.min.js、jquery.validate.min.js、favicon.ico 等二进制
- 幂等：已含 "Licensed under the Apache License" 的文件自动跳过
- 编码安全：脚本用 `[System.IO.File]::ReadAllText/WriteAllText` 显式 UTF-8（无 BOM）读写，避免中文乱码

## 改动清单

- 300 个文件：4594 增 / 97 删（删除行均为原文件首行 BOM 乱码 `锘?` 被 header 取代，属良性修正）
- 附带：`doc/需求文档.md` 遗留格式修正（表格对齐、空行，无内容实质变化）一并提交
- 临时脚本 add-license-header.ps1 已删除（不入库）

## 验证

- ✅ Java 头部正确（ConfigController.java 抽查：header 后接 package 声明）
- ✅ XML/properties/html header 位置正确
- ✅ 中文注释无乱码（UTF-8 无 BOM 读写）
- ✅ git status 干净，origin/master 已同步 979d7b9

## 交付

- 提交信息：`代码文件统一添加 Apache 2.0 license header`
- 已推送：https://github.com/yuezu1026/dataReport
