package com.vat.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vat.bean.SmsTemplateParamVO;
import com.vat.bean.SmsVO;
import com.vat.bean.SystemUser;
import com.vat.service.SmsService;

@Controller
public class SmsSecCodeController {

    private final static Logger logger = LoggerFactory.getLogger(SmsSecCodeController.class);

    @Resource
    private SmsService smsService;

    @RequestMapping(value = "/system/sendSmsCheckCode.do", method = RequestMethod.POST)
    @ResponseBody
    public String sendSmsCheckCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

	logger.info("sendSmsCheckCode  start");

	SystemUser systemUser = (SystemUser) request.getSession().getAttribute("systemUser");
	if (systemUser == null) {
	    response.sendRedirect(request.getContextPath() + "/system/login.html");
	    return null;
	}
	if (StringUtils.isBlank(systemUser.getBizId())) {
	    String code = getRandomCode();
	    String phoneNunber = systemUser.getTelphone();
	    SmsVO smsVO = new SmsVO();
	    smsVO.setPhoneNumbers(phoneNunber);
	    smsVO.setSignName("王耀华");
	    smsVO.setTemplateCode("SMS_142151654");
	    SmsTemplateParamVO smsTemplateParamVO = new SmsTemplateParamVO();
	    smsTemplateParamVO.setCode(code);
	    smsTemplateParamVO.setName(systemUser.getUsername());
	    JSONObject json = (JSONObject) JSON.toJSON(smsTemplateParamVO);
	    smsVO.setTemplateParam(json.toString());
	    SendSmsResponse sendSmsResponse = null;
	    try {
		sendSmsResponse = smsService.sendSms(smsVO);
	    } catch (Exception e) {
		logger.error("error", e);
	    }
	    String bizId = sendSmsResponse.getBizId();
	    systemUser.setBizId(bizId);
	    systemUser.setCode(code);
	}

	Map<String, Boolean> map = new HashMap<>();
	ObjectMapper mapper = new ObjectMapper();
	String resultString = null;
	try {
	    resultString = mapper.writeValueAsString(map);
	} catch (JsonProcessingException e) {
	    logger.error("error", e);
	}
	return resultString;
    }

    @RequestMapping("/system/checkSmsCode.do")
    public ModelAndView checkSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

	logger.info("checkSmsCode  start");
	String smsCode = request.getParameter("smsCode");

	SystemUser systemUser = (SystemUser) request.getSession().getAttribute("systemUser");
	if (systemUser == null) {
	    response.sendRedirect(request.getContextPath() + "/system/login.html");
	    return null;
	}
	if (StringUtils.isNotBlank(smsCode) && smsCode.equals(systemUser.getCode())) {
	    Map<String, SystemUser> userMap = new HashMap<String, SystemUser>();
	    userMap.put("systemUser", systemUser);
	    return new ModelAndView("system/index", userMap);
	} else {
	    Map<String, SystemUser> userMap = new HashMap<String, SystemUser>();
	    return new ModelAndView("system/checkCode", userMap);
	}

    }

    private String getRandomCode() {
	Integer randomNumber = (int) ((Math.random() * 9 + 1) * 100000);
	return String.valueOf(randomNumber);
    }
}