package com.vat.service;

import java.util.List;
import java.util.Set;

import com.github.pagehelper.Page;
import com.vat.bean.User;

public interface UserService {

    public void insertUser(User user);
    
    public void saveUserRole(User user);
    
    public User userLogin(String userName,String password);
    
    public User findUser(String userName);
    
    public void updateLoginTime(User user);
    
    public void updateUseTimes(User user);
    
    public User findUserById(String userId);
    
    public void reviewUser(User user);
    
    public Page<User> findUserByPage(int currentPage, int pageSize);
    
	/**
	 * 根据账号Account查询当前用户
	 * @param account
	 * @return
	 */
    	User findByAccount(String account);
	
	/**	
	 * 获取资源集合
	 * @param account
	 * @return
	 */
	Set<String> findPermissions(String account);
	
	/**
	 * 获取URL权限
	 * @param account
	 * @return
	 */
	List<String> findPermissionUrl(String account);
    
}
