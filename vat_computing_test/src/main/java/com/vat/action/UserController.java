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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vat.bean.User;
import com.vat.service.UserService;
import com.vat.util.UUIDUtils;

@Controller
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping("/user/clearUserLogin.do")
    public void clearUserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
	request.getSession().removeAttribute("user");
	response.sendRedirect(request.getContextPath() + "/login.html");
    }

    @RequestMapping("/user/login.do")
    @ResponseBody
    public String userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	User user = userService.userLogin(username, password);
	if (user != null) {
	    logger.info("登录验证通过");
	    request.getSession().setAttribute("user", user);

	    Map<String, User> userMap = new HashMap<String, User>();
	    userMap.put("user", user);
	} else {
	    response.setCharacterEncoding("utf-8");
	    PrintWriter out = response.getWriter();
	    out.print(
		    "<script>alert('login failure,  this user is not exists.'); window.location='login.html' </script>");
	    out.flush();
	    out.close();
	}
	Map<String, Object> map = new HashMap<>();
	map.put("success", true);
	map.put("message", "登录验证通过");
	ObjectMapper mapper = new ObjectMapper();
	String resultString = null;
	try {
	    resultString = mapper.writeValueAsString(map);
	} catch (JsonProcessingException e) {
	    logger.error("error", e);
	}
	return resultString;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public String userReg(HttpServletRequest request, HttpServletResponse response) throws IOException {
	boolean success = false;
	String username = request.getParameter("username");
	String password = request.getParameter("password");

	User user = userService.findUser(username);
	if (user != null) {
	    success = false;
	} else {
	    String gender = request.getParameter("gender");
	    String email = request.getParameter("email");
	    String companyName = request.getParameter("companyName");
	    String telphone = request.getParameter("telphone");

	    String userId = UUIDUtils.getUUID();
	    user = new User();
	    user.setUserId(userId);
	    user.setUsername(username);
	    user.setPassword(password);
	    user.setGender(gender);
	    user.setEmail(email);
	    user.setCompanyName(companyName);
	    user.setTelphone(telphone);
	    userService.insertUser(user);
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

    @RequestMapping(value = "/user/checkUserUnique", method = RequestMethod.POST)
    @ResponseBody
    public String checkUserNameValid(HttpServletRequest request) {

	boolean result = true;

	String username = request.getParameter("username");

	if (StringUtils.isNotEmpty(username)) {
	    User user = userService.findUser(username);
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