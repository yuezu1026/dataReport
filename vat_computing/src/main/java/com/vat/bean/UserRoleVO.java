package com.vat.bean;

import java.io.Serializable;

public class UserRoleVO implements Serializable {
    
    private static final long serialVersionUID = 5006605824491948925L;

    private String id;
    private String userId;
    private String roleId;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    


}
