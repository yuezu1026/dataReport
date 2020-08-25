package com.vat.bean;

import java.io.Serializable;

public class SystemConfigVO implements Serializable {

    private static final long serialVersionUID = 6143431823558606691L;
    
    private String sysConfigId;
    private String configCode;
    private String configDesc;
    private String configValue;
    
    public String getSysConfigId() {
        return sysConfigId;
    }
    public void setSysConfigId(String sysConfigId) {
        this.sysConfigId = sysConfigId;
    }
    public String getConfigCode() {
        return configCode;
    }
    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }
    public String getConfigDesc() {
        return configDesc;
    }
    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }
    public String getConfigValue() {
        return configValue;
    }
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
    



}
