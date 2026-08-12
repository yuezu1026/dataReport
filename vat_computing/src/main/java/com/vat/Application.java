/*
 * Copyright 2026 yuezu1026
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
