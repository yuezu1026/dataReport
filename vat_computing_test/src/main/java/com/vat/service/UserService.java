package com.vat.service;

import com.github.pagehelper.Page;
import com.vat.bean.User;

public interface UserService {

    public void insertUser(User user);
    
    public User userLogin(String userName,String password);
    
    public User findUser(String userName);
    
    public User findUserById(String userId);
    
    public void reviewUser(User user);
    
    public Page<User> findUserByPage(int currentPage, int pageSize);
    
}
