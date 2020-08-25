package com.vat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.vat.servlet.WeiXinUtils;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {
    
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) {

	ApplicationContext ctx = SpringApplication.run(Application.class, args);
	String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
	for (String profile : activeProfiles) {
		logger.warn("Spring Boot 使用profile为:{}" , profile);
	}
    }
    
    @Bean
    public ServletRegistrationBean MyServlet1(){
        return new ServletRegistrationBean(new WeiXinUtils(),"/myserv/*");
    }
    
}
