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

public class CountryVO implements Serializable {
    private static final long serialVersionUID = 3213754439080876489L;

    private String countryCode;
    private String countryName;
    private String taxFlag;
    private String currencyCode;
    private String standardVatRate;
    private String lowVatRate;;
    private String create_date;
    private String flag;
    
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public String getTaxFlag() {
        return taxFlag;
    }
    public void setTaxFlag(String taxFlag) {
        this.taxFlag = taxFlag;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getStandardVatRate() {
        return standardVatRate;
    }
    public void setStandardVatRate(String standardVatRate) {
        this.standardVatRate = standardVatRate;
    }
    public String getLowVatRate() {
        return lowVatRate;
    }
    public void setLowVatRate(String lowVatRate) {
        this.lowVatRate = lowVatRate;
    }
    public String getCreate_date() {
        return create_date;
    }
    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    
}
