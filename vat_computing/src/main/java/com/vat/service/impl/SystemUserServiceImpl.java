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
package com.vat.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.vat.bean.SystemUser;
import com.vat.mapper.SystemUserMapper;
import com.vat.service.SystemUserService;

@ComponentScan({ "com.vat.mapper" })
@Service("systemUserService")
public class SystemUserServiceImpl implements SystemUserService {

    private final static Logger logger = LoggerFactory.getLogger(SystemUserServiceImpl.class);
   
    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    public void insertSystemUser(SystemUser user) {
	systemUserMapper.insertSystemUser(user);;
    }

    public SystemUser userSystemLogin(String userName, String password) {
	SystemUser systemUser = systemUserMapper.findSystemUserPassword(userName, password);
	if (systemUser != null) {
	    logger.info("系统用户验证通过");
	}
	return systemUser;
    }
    
    public SystemUser findSystemUser(String userName) {
	SystemUser systemUser = systemUserMapper.findSystemUser(userName);
   	if (systemUser != null) {
   	    logger.info("系统用户验证通过");
   	}
   	return systemUser;
       }

}
