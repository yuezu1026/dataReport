package com.vat.bean;

import java.io.Serializable;

public class SmsTemplateParamVO  implements Serializable{

    private static final long serialVersionUID = 291096828295984479L;

    private String name;
    
    private String code;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
