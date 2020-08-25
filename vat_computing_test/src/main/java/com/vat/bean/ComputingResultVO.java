package com.vat.bean;

import java.io.Serializable;

public class ComputingResultVO implements Serializable {
  
    private static final long serialVersionUID = -3724246464682016897L;
    private String resultId;
    private String userId;
    private String dataType;
    private String userAccount;
    private String period;
    private String periodDate;
    private String needComputingCountry;
    private String needComputingCountryCode;
    private String currencyCode;
    private String includingTaxAmount;
    private String excludingTaxAmount;
    private String vatAmount;
    private String createDate;
    
    public String getResultId() {
        return resultId;
    }
    public void setResultId(String resultId) {
        this.resultId = resultId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNeedComputingCountry() {
        return needComputingCountry;
    }
    public void setNeedComputingCountry(String needComputingCountry) {
        this.needComputingCountry = needComputingCountry;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getIncludingTaxAmount() {
        return includingTaxAmount;
    }
    public void setIncludingTaxAmount(String includingTaxAmount) {
        this.includingTaxAmount = includingTaxAmount;
    }
    public String getExcludingTaxAmount() {
        return excludingTaxAmount;
    }
    public void setExcludingTaxAmount(String excludingTaxAmount) {
        this.excludingTaxAmount = excludingTaxAmount;
    }
    public String getVatAmount() {
        return vatAmount;
    }
    public void setVatAmount(String vatAmount) {
        this.vatAmount = vatAmount;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getUserAccount() {
        return userAccount;
    }
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    public String getPeriod() {
        return period;
    }
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public void setPeriod(String period) {
        this.period = period;
    }
    public String getNeedComputingCountryCode() {
        return needComputingCountryCode;
    }
    public void setNeedComputingCountryCode(String needComputingCountryCode) {
        this.needComputingCountryCode = needComputingCountryCode;
    }
    public String getPeriodDate() {
        return periodDate;
    }
    public void setPeriodDate(String periodDate) {
        this.periodDate = periodDate;
    }

}
