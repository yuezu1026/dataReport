package com.vat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.vat.bean.User;


@Mapper
public interface UserMapper {

    public void insertUser(@Param("user") User user);
    
    public void reviewUser(@Param("user") User user);
    

    public void update(User user);
    
    public void delete(int id);
    
    public User findUserPassword(@Param("username") String userName,@Param("password") String password);
    
    public User findUser(@Param("username") String userName);
    
    public User findUserById(@Param("userId") String userId);
    
    public Page<User> findUserByPage(@Param("user") User user );
    
    
    
}