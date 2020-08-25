package com.vat.bean;

import java.io.Serializable;

public class SmsVO implements Serializable {


    private static final long serialVersionUID = -1680726757303600342L;
    
    private String phoneNumbers;
    
    private String signName;
    
    private String templateCode;
    
    private String templateParam;

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }

}
