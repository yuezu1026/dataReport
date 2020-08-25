package com.vat.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	Subject subject = SecurityUtils.getSubject();
	subject.logout();
	
	response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    @RequestMapping("/user/login.do")
    @ResponseBody
    public String userLogin(HttpServletRequest request, HttpServletResponse response, RedirectAttributes rediect) throws IOException {
	
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	//String d = findMd5Password(password);
	String rememberMe = request.getParameter("rememberMe"); 
	boolean isRememberMe = false;
	if("1".equals(rememberMe)) {
	    isRememberMe = true;
	}
	UsernamePasswordToken upt = new UsernamePasswordToken(username, password,isRememberMe);
	Subject subject = SecurityUtils.getSubject();
	try {
		subject.login(upt);
		
	} catch (AuthenticationException e) {
		logger.error("eror",e);
		rediect.addFlashAttribute("errorText", "您的账号或密码输入错误!");
		return "redirect:/user/login.do";
	}
	//return "redirect:/index";
	password = findMd5Password(password);
	User user = userService.findUser(username);
	if (user != null) {
	    logger.info("登录验证通过");
	    userService.updateLoginTime(user);
	    request.getSession().setAttribute("user", user);

	    Map<String, User> userMap = new HashMap<String, User>();
	    userMap.put("user", user);
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
    
    private String findMd5Password(String credentials) {
	 	String hashAlgorithmName = "MD5";
	        //String credentials = "123456";
	        //int hashIterations = 1024;
	        Object obj = new SimpleHash(hashAlgorithmName, credentials);
	        logger.info("password: "+obj);
	        return obj.toString();
    }

    @RequestMapping(value = "/user/register.do", method = RequestMethod.POST)
    @ResponseBody
    public String userReg(HttpServletRequest request, HttpServletResponse response) throws IOException {
	boolean success = false;
	String username = request.getParameter("username");
	String password = request.getParameter("password");

	String credentials = findMd5Password(password);
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
	    user.setPassword(credentials);
	    user.setGender(gender);
	    user.setEmail(email);
	    user.setCompanyName(companyName);
	    user.setTelphone(telphone);
	    userService.insertUser(user);
	    user.setRoleId("1");
	    userService.saveUserRole(user);
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

    @RequestMapping(value = "/user/checkUserUnique.do", method = RequestMethod.POST)
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