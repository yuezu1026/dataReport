package com.vat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.vat.bean.User;
import com.vat.bean.UserRoleVO;


@Mapper
public interface UserMapper {

    public void insertUser(@Param("user") User user);
    
    public void insertUserRole(@Param("user") User user);
    
    public List<UserRoleVO> findUserRole(@Param("user") User user);
    
    public void updateUserRole(@Param("user") User user);
    
    public void reviewUser(@Param("user") User user);
    
    public User findUserPassword(@Param("username") String userName,@Param("password") String password);
    
    public User findUser(@Param("username") String userName);
    
    public void updateUserLoginTimes(@Param("user") User user);
    
    public void updateUseTimes(@Param("user") User user);
    
    
    public User findUserById(@Param("userId") String userId);
    
    public Page<User> findUserByPage(@Param("user") User user );
    
    
    
}