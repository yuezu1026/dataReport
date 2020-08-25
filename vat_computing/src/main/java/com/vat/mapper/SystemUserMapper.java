package com.vat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.vat.bean.SystemUser;


@Mapper
public interface SystemUserMapper {

    public void insertSystemUser(@Param("user") SystemUser user);
    
    public SystemUser findSystemUserPassword(@Param("username") String userName,@Param("password") String password);
    
    public SystemUser findSystemUser(@Param("username") String userName);
    
}