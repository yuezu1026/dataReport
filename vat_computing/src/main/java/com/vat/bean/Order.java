package com.vat.bean;

import java.io.Serializable;

public class Order implements Serializable {

    private static final long serialVersionUID = -2020159001519304251L;

    String appid;
    String outTradeNo;
    String openId;
    String mchId;
    String totalFee;
    String cashFee;
    String feeType;
    String resultCode;
    String errCode;
    String errCodeDes;
    String isSubscribe;
    String tradeType;
    String bankType;
    String transactionId;
    String couponId;
    String couponFee;
    String couponCount;
    String attach;
    String timeEnd;
    String couresCount;
    String couresId;
    String url;

    public String getAppid() {
	return appid;
    }

    public void setAppid(String appid) {
	this.appid = appid;
    }

    public String getOutTradeNo() {
	return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
	this.outTradeNo = outTradeNo;
    }

    public String getOpenId() {
	return openId;
    }

    public void setOpenId(String openId) {
	this.openId = openId;
    }

    public String getMchId() {
	return mchId;
    }

    public void setMchId(String mchId) {
	this.mchId = mchId;
    }

    public String getTotalFee() {
	return totalFee;
    }

    public void setTotalFee(String totalFee) {
	this.totalFee = totalFee;
    }

    public String getCashFee() {
	return cashFee;
    }

    public void setCashFee(String cashFee) {
	this.cashFee = cashFee;
    }

    public String getFeeType() {
	return feeType;
    }

    public void setFeeType(String feeType) {
	this.feeType = feeType;
    }

    public String getResultCode() {
	return resultCode;
    }

    public void setResultCode(String resultCode) {
	this.resultCode = resultCode;
    }

    public String getErrCode() {
	return errCode;
    }

    public void setErrCode(String errCode) {
	this.errCode = errCode;
    }

    public String getErrCodeDes() {
	return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
	this.errCodeDes = errCodeDes;
    }

    public String getIsSubscribe() {
	return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
	this.isSubscribe = isSubscribe;
    }

    public String getTradeType() {
	return tradeType;
    }

    public void setTradeType(String tradeType) {
	this.tradeType = tradeType;
    }

    public String getBankType() {
	return bankType;
    }

    public void setBankType(String bankType) {
	this.bankType = bankType;
    }

    public String getTransactionId() {
	return transactionId;
    }

    public void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
    }

    public String getCouponId() {
	return couponId;
    }

    public void setCouponId(String couponId) {
	this.couponId = couponId;
    }

    public String getCouponFee() {
	return couponFee;
    }

    public void setCouponFee(String couponFee) {
	this.couponFee = couponFee;
    }

    public String getCouponCount() {
	return couponCount;
    }

    public void setCouponCount(String couponCount) {
	this.couponCount = couponCount;
    }

    public String getAttach() {
	return attach;
    }

    public void setAttach(String attach) {
	this.attach = attach;
    }

    public String getTimeEnd() {
	return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
	this.timeEnd = timeEnd;
    }

    public String getCouresCount() {
	return couresCount;
    }

    public void setCouresCount(String couresCount) {
	this.couresCount = couresCount;
    }

    public String getCouresId() {
	return couresId;
    }

    public void setCouresId(String couresId) {
	this.couresId = couresId;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

}
