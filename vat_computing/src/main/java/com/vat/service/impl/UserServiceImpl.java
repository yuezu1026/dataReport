package com.vat.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.vat.bean.ModuleInfo;
import com.vat.bean.User;
import com.vat.bean.UserRoleVO;
import com.vat.mapper.UserMapper;
import com.vat.service.ModuleService;
import com.vat.service.UserService;

@ComponentScan({ "com.vat.mapper" })
@Service("userService")
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
   
    @Resource
    private UserMapper userMapper;
    
    @Autowired
    private ModuleService moduleService;

    @Override
    public void insertUser(User user) {
	userMapper.insertUser(user);
    }
    
    @Override
    public void saveUserRole(User user) {
	List<UserRoleVO> userRoleList = userMapper.findUserRole(user);
	if(CollectionUtils.isEmpty(userRoleList)) {
	    userMapper.insertUserRole(user);
	}else {
	    UserRoleVO userRoleVO =  userRoleList.get(0);
	    if(!StringUtils.equals(userRoleVO.getRoleId(), user.getRoleId())) {
		userMapper.updateUserRole(user);
	    }
	}
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
    
    public void updateLoginTime(User user) {
	userMapper.updateUserLoginTimes(user);
    }
    
    public void updateUseTimes(User user) {
	userMapper.updateUseTimes(user);
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

    @Override
    public User findByAccount(String account) {
	User user = userMapper.findUser(account);
   	return user;
    }

    @Override
    public Set<String> findPermissions(String account) {
	Set<String> set = Sets.newHashSet();
	User user = findByAccount(account);
	List<ModuleInfo>modules = moduleService.findModuleByUserId(user.getUserId());
	
	for(ModuleInfo info: modules) {
		set.add(info.getModuleKey());
	}
	return set;
    }

    @Override
    public List<String> findPermissionUrl(String account) {
	List<String> list = Lists.newArrayList();
	User user = findByAccount(account);
	List<ModuleInfo>modules = moduleService.findModuleByUserId(user.getUserId());
	if(CollectionUtils.isNotEmpty(modules)){
        	for(ModuleInfo info: modules) {
        		if(info != null && info.getModuleType() == ModuleInfo.URL_TYPE) {
        			list.add(info.getModulePath());
        		}
        	}
	}
	return list;
    }

}
