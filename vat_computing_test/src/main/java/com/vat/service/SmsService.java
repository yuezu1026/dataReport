package com.vat.service;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.vat.bean.SmsVO;

public interface SmsService {

    SendSmsResponse sendSms(SmsVO smsVO) throws ClientException;

    QuerySendDetailsResponse querySendDetails(String bizId, String phoneNunber) throws ClientException;

}
