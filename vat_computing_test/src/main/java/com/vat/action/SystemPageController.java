package com.vat.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.vat.bean.SystemUser;
import com.vat.bean.User;
import com.vat.service.UserService;
import com.vat.util.DateUtil;

@Controller
public class SystemPageController {

    private final static Logger logger = LoggerFactory.getLogger(SystemPageController.class);

    @Resource
    private UserService userService;

    @RequestMapping("/system/user.do")
    public ModelAndView user(HttpServletRequest request, HttpServletResponse response) throws IOException {

	logger.info("user admin start");

	SystemUser systemUser = (SystemUser) request.getSession().getAttribute("systemUser");
	if (systemUser == null) {
	    response.sendRedirect(request.getContextPath() + "/system/login.html");
	    return null;
	}
	Map<String, Object> map = new HashMap<String, Object>();
	String code =systemUser.getCode();
	if(StringUtils.isEmpty(code)) {
	    return new ModelAndView("system/checkCode", map);
	}
	String page = request.getParameter("page");

	int currentPage = 1;
	if (StringUtils.isNotBlank(page)) {
	    currentPage = Integer.parseInt(page);
	}
	if (currentPage < 0) {
	    currentPage = 1;
	}
	int pageSize = 15;
	Page<User> userPage = userService.findUserByPage(currentPage, pageSize);

	if (userPage != null) {
	    List<User> pdfList = userPage.getResult();
	    map.put("currentPage", userPage.getPageNum());
	    map.put("totalPages", userPage.getPages());
	    map.put("size", pageSize);
	    if (CollectionUtils.isNotEmpty(pdfList)) {
		map.put("userList", pdfList);
	    }
	}
	return new ModelAndView("system/user", map);
    }

    @RequestMapping("/system/userReview.do")
    public ModelAndView userReview(HttpServletRequest request, HttpServletResponse response) throws IOException {

	logger.info("userReview start");

	SystemUser systemUser = (SystemUser) request.getSession().getAttribute("systemUser");
	if (systemUser == null) {
	    response.sendRedirect(request.getContextPath() + "/system/login.html");
	    return null;
	}
	Map<String, Object> map = new HashMap<String, Object>();
	String code =systemUser.getCode();
	if(StringUtils.isEmpty(code)) {
	    return new ModelAndView("system/checkCode", map);
	}

	String userId = request.getParameter("userId");
	User user = userService.findUserById(userId);
	if (user != null) {
	    String passFlag = user.getPassFlag();
	    if ("N".equals(passFlag)) {
		user.setPassFlag("Y");
		String startServiceDate = DateUtil.getYyyy_MM_ddDate();
		user.setStartServiceDate(startServiceDate);
		String endServiceDate = DateUtil.getNextYearDate();
		user.setEndServiceDate(endServiceDate);
	    } else {
		user.setPassFlag("N");
	    }
	    userService.reviewUser(user);
	}

	
	String page = request.getParameter("page");

	int currentPage = 1;
	if (StringUtils.isNotBlank(page)) {
	    currentPage = Integer.parseInt(page);
	}
	if (currentPage < 0) {
	    currentPage = 1;
	}
	int pageSize = 15;
	Page<User> userPage = userService.findUserByPage(currentPage, pageSize);

	if (userPage != null) {
	    List<User> pdfList = userPage.getResult();
	    map.put("currentPage", userPage.getPageNum());
	    map.put("totalPages", userPage.getPages());
	    map.put("size", pageSize);
	    if (CollectionUtils.isNotEmpty(pdfList)) {
		map.put("userList", pdfList);
	    }
	}
	return new ModelAndView("system/user", map);
    }

    @RequestMapping("/system/index.do")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {

	logger.info("index  start");

	SystemUser systemUser = (SystemUser) request.getSession().getAttribute("systemUser");
	if (systemUser == null) {
	    response.sendRedirect(request.getContextPath() + "/system/login.html");
	    return null;
	}
	Map<String, SystemUser> userMap = new HashMap<String, SystemUser>();
	userMap.put("systemUser", systemUser);
	return new ModelAndView("system/index", userMap);
    }

}
