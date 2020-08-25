package com.vat.action;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "UnifiedOrderRespose")
@XmlType(propOrder={"return_code","return_msg","appid","mch_id","device_info","nonce_str","sign","result_code","err_code","err_code_des","trade_type","prepay_id","code_url"})
public class UnifiedOrderRespose {
    
    private String return_code; 
    //返回状态码
    private String return_msg;
    //返回信息
    private String appid; 
    //公众账号ID
    private String mch_id; 
    //商户号
    private String device_info; 
    //设备号
    private String nonce_str;
    //随机字符串
    private String sign; //数字签名
    private String result_code; //业务结果
    private String err_code; //错误代码
    private String err_code_des; // 错误代码描述
    private String trade_type; //交易类型
    private String prepay_id; //预支付交易会话标致
    private String code_url; //二维码链接
    public UnifiedOrderRespose(){}
    public UnifiedOrderRespose(String return_code, String return_msg,String appid, String mch_id, String device_info, String nonce_str,String sign, String result_code, String err_code,String err_code_des, String trade_type, String prepay_id,String code_url) {
	super();
	this.return_code = return_code;
	this.return_msg = return_msg;
	this.appid = appid;
	this.mch_id = mch_id;
	this.device_info = device_info;
	this.nonce_str = nonce_str;
	this.sign = sign;
	this.result_code = result_code;
	this.err_code = err_code;
	this.err_code_des = err_code_des;
	this.trade_type = trade_type;
	this.prepay_id = prepay_id;
	this.code_url = code_url;
	}
}