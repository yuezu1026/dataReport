package com.vat.config;

import java.util.Properties;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

@Configuration
public class CommonConfig {

    @Autowired
    private Environment env;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
	MultipartConfigFactory factory = new MultipartConfigFactory();
	factory.setMaxFileSize(100 * 1024L * 1024L);
	return factory.createMultipartConfig();
    }

    @Bean
    public PageHelper pageHelper() {
	PageHelper pageHelper = new PageHelper();
	Properties properties = new Properties();
	properties.setProperty("offsetAsPageNum", "true");
	properties.setProperty("rowBoundsWithCount", "true");
	properties.setProperty("reasonable", "true");
	properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言
	pageHelper.setProperties(properties);
	return pageHelper;
    }

    @Bean
    public DataSource dataSource() {
	DruidDataSource dataSource = new DruidDataSource();
	dataSource.setUrl(env.getProperty("spring.datasource.url"));
	// 用户名
	dataSource.setUsername(env.getProperty("spring.datasource.username"));
	dataSource.setPassword(env.getProperty("spring.datasource.password"));// 密码
	dataSource.setInitialSize(2);
	dataSource.setMaxActive(20);
	dataSource.setMinIdle(0);
	dataSource.setMaxWait(60000);
	dataSource.setValidationQuery("SELECT 1");
	dataSource.setTestOnBorrow(false);
	dataSource.setTestWhileIdle(true);
	dataSource.setPoolPreparedStatements(false);
	return dataSource;
    }
    
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer(){
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
            }
        };
    }
}