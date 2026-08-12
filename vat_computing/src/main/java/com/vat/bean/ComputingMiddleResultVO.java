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

public class ComputingMiddleResultVO implements Serializable {
  
    private static final long serialVersionUID = -4134224864964929799L;
    
    private String computingId;
    private String userId;
    private String dataType;
    private String period;
    private String needComputingCountry;
    private String computingType;
    private String currencyCode;
    private String amount;
    private String createDate;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }
    public String getComputingId() {
        return computingId;
    }
    public void setComputingId(String computingId) {
        this.computingId = computingId;
    }
    public String getNeedComputingCountry() {
        return needComputingCountry;
    }
    public void setNeedComputingCountry(String needComputingCountry) {
        this.needComputingCountry = needComputingCountry;
    }
    public String getComputingType() {
        return computingType;
    }
    public void setComputingType(String computingType) {
        this.computingType = computingType;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }


}
