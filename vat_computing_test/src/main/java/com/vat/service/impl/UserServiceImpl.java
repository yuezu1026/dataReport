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

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vat.bean.User;
import com.vat.mapper.UserMapper;
import com.vat.service.UserService;

@ComponentScan({ "com.vat.mapper" })
@Service("userService")
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
   
    @Resource
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
	userMapper.insertUser(user);
    }
    
    @Override
    public void reviewUser(User user) {
	userMapper.reviewUser(user);
    }
    
    

    public User userLogin(String userName, String password) {
	User user = userMapper.findUserPassword(userName, password);
	if (user != null) {
	    logger.info("用户验证通过");
	}
	return user;
    }
    
    public User findUser(String userName) {
   	User user = userMapper.findUser(userName);
   	if (user != null) {
   	    logger.info("用户验证通过");
   	}
   	return user;
       }
    
    
    public User findUserById(String userId) {
   	User user = userMapper.findUserById(userId);
   	return user;
       }
    
    @Override
    public Page<User> findUserByPage(int currentPage, int pageSize) {
	User user = new User();
	PageHelper.startPage(currentPage, pageSize);
	Page<User> pageInfo = userMapper.findUserByPage(user);
	return pageInfo;
    }

}
