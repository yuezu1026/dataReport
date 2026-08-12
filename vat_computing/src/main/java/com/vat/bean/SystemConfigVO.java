/*
 * Copyright 2026 yuezu1026
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
