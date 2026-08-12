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

public class ExchangeRateVO implements Serializable {

    private static final long serialVersionUID = -5832147230944327377L;
    
    private String period;
    private String fromCurrencyCode;
    private String toCurrencyCode;
    private String rate;
    
    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }
    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }
    public void setFromCurrencyCode(String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }
    public String getToCurrencyCode() {
        return toCurrencyCode;
    }
    public void setToCurrencyCode(String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    

    
}
