package com.vat.bean;

import java.io.Serializable;

public class ParamConfigVO implements Serializable {

    private static final long serialVersionUID = 6143431823558606691L;
    
    private String paramId;
    private String userId;
    private String computingMethod;
    
    public String getParamId() {
        return paramId;
    }
    public void setParamId(String paramId) {
        this.paramId = paramId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getComputingMethod() {
        return computingMethod;
    }
    public void setComputingMethod(String computingMethod) {
        this.computingMethod = computingMethod;
    }
    



}
