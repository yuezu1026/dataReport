package com.vat.service;

import com.vat.bean.SystemUser;

public interface SystemUserService {

    public void insertSystemUser(SystemUser user);
    
    public SystemUser userSystemLogin(String userName,String password);
    
    public SystemUser findSystemUser(String userName);
    
}
