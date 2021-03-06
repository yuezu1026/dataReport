package com.vat.bean;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 5033668513469773260L;
    
    private String userId;
    private String username;
    private String password;
    
    private String gender;
    private String email;
    private String companyName;
    private String telphone;
    private String passFlag;
    
    private String startServiceDate;
    private String endServiceDate;
    private String createDate;

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassFlag() {
        return passFlag;
    }

    public void setPassFlag(String passFlag) {
        this.passFlag = passFlag;
    }

    public String getStartServiceDate() {
        return startServiceDate;
    }

    public void setStartServiceDate(String startServiceDate) {
        this.startServiceDate = startServiceDate;
    }

    public String getEndServiceDate() {
        return endServiceDate;
    }

    public void setEndServiceDate(String endServiceDate) {
        this.endServiceDate = endServiceDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }



}
