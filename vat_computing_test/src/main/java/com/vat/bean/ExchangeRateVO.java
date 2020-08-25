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
