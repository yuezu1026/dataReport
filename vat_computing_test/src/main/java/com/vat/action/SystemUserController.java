package com.vat.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vat.bean.SystemUser;
import com.vat.service.SystemUserService;
import com.vat.util.UUIDUtils;

@Controller
public class SystemUserController {

    private final static Logger logger = LoggerFactory.getLogger(SystemUserController.class);

    @Resource
    private SystemUserService systemUserService;

    @RequestMapping("/system/clearUserLogin.do")
    public void clearUserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
	request.getSession().removeAttribute("systemUser");
	response.sendRedirect(request.getContextPath() + "/system/login.html");
    }

    @RequestMapping("/system/login.do")
    public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	SystemUser user = systemUserService.userSystemLogin(username, password);
	if (user != null) {
	    logger.info("登录验证通过");
	    request.getSession().setAttribute("systemUser", user);

	    Map<String, SystemUser> userMap = new HashMap<String, SystemUser>();
	    userMap.put("user", user);
	    return new ModelAndView("system/checkCode", userMap);
	} else {
	    response.setCharacterEncoding("utf-8");
	    PrintWriter out = response.getWriter();
	    out.print(
		    "<script>alert('login failure,  this user is not exists.'); window.location='/system/login.html' </script>");
	    out.flush();
	    out.close();
	}
	return null;
    }

    @RequestMapping(value = "/system/register.do", method = RequestMethod.POST)
    @ResponseBody
    public String userReg(HttpServletRequest request, HttpServletResponse response) throws IOException {
	boolean success = false;
	String username = request.getParameter("username");
	String password = request.getParameter("password");

	SystemUser user = systemUserService.findSystemUser(username);
	if (user != null) {
	    success = false;
	} else {
	    String telphone = request.getParameter("telphone");

	    String sytemUserId = UUIDUtils.getUUID();
	    user = new SystemUser();
	    user.setSystemUserId(sytemUserId);
	    user.setUsername(username);
	    user.setPassword(password);
	    user.setTelphone(telphone);
	    systemUserService.insertSystemUser(user);
	    success = true;
	}
	Map<String, Boolean> map = new HashMap<>();
	map.put("success", success);
	ObjectMapper mapper = new ObjectMapper();
	String resultString = null;
	try {
	    resultString = mapper.writeValueAsString(map);
	} catch (JsonProcessingException e) {
	    logger.error("error", e);
	}
	return resultString;

    }

    @RequestMapping(value = "/system/checkUserUnique.do", method = RequestMethod.POST)
    @ResponseBody
    public String checkUserNameValid(HttpServletRequest request) {

	boolean result = true;

	String username = request.getParameter("username");

	if (StringUtils.isNotEmpty(username)) {
	    SystemUser user = systemUserService.findSystemUser(username);
	    if (user != null) {
		result = false;
	    }
	}
	Map<String, Boolean> map = new HashMap<>();
	map.put("valid", result);
	ObjectMapper mapper = new ObjectMapper();
	String resultString = null;
	try {
	    resultString = mapper.writeValueAsString(map);
	} catch (JsonProcessingException e) {
	    logger.error("error", e);
	}
	return resultString;
    }

}