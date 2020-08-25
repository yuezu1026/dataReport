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
