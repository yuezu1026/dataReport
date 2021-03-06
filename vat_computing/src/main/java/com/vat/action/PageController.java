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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.vat.bean.PDFReportVO;
import com.vat.bean.ParamConfigVO;
import com.vat.bean.SystemConfigVO;
import com.vat.bean.User;
import com.vat.mapper.SystemConfigMapper;
import com.vat.service.ComputingService;
import com.vat.service.ConfigService;
import com.vat.service.UserService;

@Controller
public class PageController {

    private final static Logger logger = LoggerFactory.getLogger(PageController.class);
    
    @Resource
    @Qualifier("computingAmazonServiceImpl")
    private ComputingService computingAmazonService;

    @Resource
    @Qualifier("computingGBServiceImpl")
    private ComputingService computingGBService;
    
    @Resource
    private UserService userService;
    
    @Resource
    private ConfigService configService;
    
    
    @Resource
    private SystemConfigMapper systemConfigMapper;
    
    
    @RequestMapping("/importPage")
    public ModelAndView importPage(HttpServletRequest request ,HttpServletResponse response) throws IOException{
	
	logger.info("importPage start");
	
	User user =(User) request.getSession().getAttribute("user");
	if(user == null) {
	    response.sendRedirect(request.getContextPath()+"/login.jsp");
	    return null;
	}
	Map<String,User> userMap = new HashMap<String,User>();
	userMap.put("user", user);
	return new ModelAndView("importPage",userMap);
	
    }
    
    @RequestMapping("/vat/reg_success")
    public ModelAndView regSuccess(HttpServletRequest request ,HttpServletResponse response) throws IOException{
	
	logger.info("regSuccess start");
	Map<String,String> userMap = new HashMap<String,String>();
	userMap.put("reg_success", "1");
	return new ModelAndView("vat/reg_success",userMap);
	
    }
    
    @RequestMapping("/vat/userReg.do")
    public ModelAndView userReg(HttpServletRequest request ,HttpServletResponse response) throws IOException{
	
	logger.info("userReg start");
	Map<String,String> userMap = new HashMap<String,String>();
	userMap.put("userReg", "1");
	return new ModelAndView("vat/reg",userMap);
	
    }

    @RequestMapping("/vat/index.do")
    public ModelAndView index(HttpServletRequest request ,HttpServletResponse response) throws IOException{
	
	logger.info("index start");
	
	User user =(User) request.getSession().getAttribute("user");
	if(user == null) {
	    response.sendRedirect(request.getContextPath()+"/login.jsp");
	    return null;
	}
	Map<String,User> userMap = new HashMap<String,User>();
	userMap.put("user", user);
	return new ModelAndView("vat/index",userMap);
    }
    
    
    @RequestMapping("/vat/config.do")
    public ModelAndView config(HttpServletRequest request ,HttpServletResponse response) throws IOException{
	
	logger.info("config start");
	
	User user =(User) request.getSession().getAttribute("user");
	if(user == null) {
	    response.sendRedirect(request.getContextPath()+"/login.jsp");
	    return null;
	}
	Map<String,Object> userMap = new HashMap<String,Object>();
	String computingMethod= null;
	ParamConfigVO paramConfigVO = configService.findConfig(user.getUserId());
	if(paramConfigVO != null) {
	    computingMethod = paramConfigVO.getComputingMethod();
	    userMap.put("computingMethod", computingMethod);
	}
	userMap.put("user", user);
	return new ModelAndView("vat/config",userMap);
    }

    
    @GetMapping("/vat/import_data.do")
    public ModelAndView importData(HttpServletRequest request ,HttpServletResponse response) throws IOException {
	
	logger.info("import_data start");
	
	User user =(User) request.getSession().getAttribute("user");
	if(user == null) {
	    response.sendRedirect(request.getContextPath()+"/login.jsp");
	    return null;
	}
	Map<String,Object> userMap = new HashMap<String,Object>();
	 Integer minimumTimes = findMinimumTimes();
	 userMap.put("minimumTimes", minimumTimes);
	
	user = userService.findUserById(user.getUserId());
	Integer usedTime = user.getUseTimes();
	if(usedTime >= minimumTimes) {
	    //超过使用次数
	    userMap.put("beyondUsedTimes", "Y");
	}else {
	    userMap.put("beyondUsedTimes", "N");
	}
	userMap.put("user", user);
	
	
	return new ModelAndView("vat/import_data",userMap);
    }
    
    @GetMapping("/vat/computing.do")
    public ModelAndView computing(HttpServletRequest request ,HttpServletResponse response) throws Exception {
	
	logger.info("computing start");
	
	Map<String, Object> map = new HashMap<>();
	
	User user =(User) request.getSession().getAttribute("user");
	String userId = null;
	if(user != null) {
	    userId = user.getUserId();
	}else {
	    response.sendRedirect(request.getContextPath()+"/login.jsp");
	    return null;
	}
	
	Map amazonMap = computingAmazonService.findAmazonBaseInfo(userId);
	
	Map gbMap = computingGBService.findGBBaseInfo(userId);
	
	map.put("user", user);
	map.put("amazonMap", amazonMap);
	map.put("gbMap", gbMap);
	return new ModelAndView("vat/computing", map );
	
    }
    
    @RequestMapping(value="/vat/findComputingParam.do", method = RequestMethod.GET)
    @ResponseBody
    public Map findComputingParam(HttpServletRequest request ,HttpServletResponse response) throws Exception {
	
	logger.info("findComputingParam start");
	
	Map<String, Object> map = new HashMap<>();
	
	User user =(User) request.getSession().getAttribute("user");
	String userId = null;
	if(user != null) {
	    userId = user.getUserId();
	}else {
	    response.sendRedirect(request.getContextPath()+"/login.jsp");
	    return null;
	}
	Map amazonMap = computingAmazonService.findAmazonBaseInfo(userId);
	Map gbMap = computingGBService.findGBBaseInfo(userId);
	map.put("user", user);
	map.put("amazonMap", amazonMap);
	map.put("gbMap", gbMap);
	return map;
    }
    
    @RequestMapping(value = "/vat/computingResult.do", method = RequestMethod.POST)
    @ResponseBody
    public Map amazonResult(HttpServletRequest request ,HttpServletResponse response) throws IOException {
	String dataType = request.getParameter("dataType");
	String rows = request.getParameter("rows");
	User user =(User) request.getSession().getAttribute("user");
	if(user == null) {
	    response.sendRedirect(request.getContextPath()+"/login.jsp");
	    return null;
	}
	String page = request.getParameter("page");
	String pageSize = request.getParameter("rows");
	
	/*Enumeration enu=request.getParameterNames();  
	while(enu.hasMoreElements()){  
	String paraName=(String)enu.nextElement();  
	System.out.println(paraName+": "+request.getParameter(paraName));  
	}  */
	
	String userId = user.getUserId();
	if(StringUtils.isEmpty(dataType)) {
	    dataType = "Amazon";
	}
	
	Map amazonMap = null;;
	
	if("Amazon".equals(dataType)) {
	     amazonMap = findAmazonResult( userId , page,pageSize);
	}
	if("GB".equals(dataType)) {
	     amazonMap = findGBResult( userId , page);
	}
	return amazonMap;
    }
    
    @GetMapping("/vat/result.do")
    public ModelAndView result(HttpServletRequest request ,HttpServletResponse response) throws IOException {
	
	logger.info("computing result start");
	User user =(User) request.getSession().getAttribute("user");
	if(user == null) {
	    response.sendRedirect(request.getContextPath()+"/login.jsp");
	    return null;
	}
	Map<String, Object> map = new HashMap<String,Object>();
	String page = request.getParameter("page");
	String strPageSize = request.getParameter("rows");
	
/*	Enumeration enu=request.getParameterNames();  
	while(enu.hasMoreElements()){  
	String paraName=(String)enu.nextElement();  
	System.out.println(paraName+": "+request.getParameter(paraName));  
	} */ 

	String userId = user.getUserId();
	String dataType = request.getParameter("dataType");
	if(StringUtils.isEmpty(dataType)) {
	    dataType = "Amazon";
	}
	if("Amazon".equals(dataType)) {
	    Map amazonMap = findAmazonResult( userId , page,strPageSize);
	    map.put("amazonMap", amazonMap);
	}
	
	if("GB".equals(dataType)) {
	    Map gbMap = findGBResult( userId , page);
	    map.put("gbMap", gbMap);
	}
	
	if("Ebay".equals(dataType)) {
	    Map ebayMap = findEbayResult( userId , page);
	    map.put("amazonMap", ebayMap);
	}
	
	map.put("user", user);
	return new ModelAndView("vat/result",map);
    }
    
    private Map<String, Object> findAmazonResult(String userId , String page,String strPageSize) {
	Map<String, Object> map = new HashMap<String,Object>();
	
	int currentPage = 1;
	if(StringUtils.isNotBlank(page)) {
	    currentPage = Integer.parseInt(page);
	}
	
	if(currentPage <0) {
	    currentPage = 1;
	}
	int pageSize = 10;
	if(StringUtils.isNotBlank(strPageSize)) {
	    pageSize = Integer.valueOf(strPageSize);
	}
	
	Page<PDFReportVO> pdfReportPage = computingAmazonService.findPDFResultByPage(userId, currentPage, pageSize);

	if(pdfReportPage != null) {
	    List<PDFReportVO> pdfList = pdfReportPage.getResult();
	    map.put("total", pdfReportPage.getTotal());
	    map.put("size", pageSize);
	    if(CollectionUtils.isNotEmpty(pdfList)) {
		map.put("rows", pdfList);
	    }
	}
	return map;
    }
    
    
    private Map<String, Object> findGBResult(String userId , String page) {
	Map<String, Object> map = new HashMap<String,Object>();
	
	int currentPage = 1;
	if(StringUtils.isNotBlank(page)) {
	    currentPage = Integer.parseInt(page);
	}
	
	if(currentPage <0) {
	    currentPage = 1;
	}
	int pageSize = 15;
	Page<PDFReportVO> pdfReportPage = computingGBService.findPDFResultByPage(userId, currentPage, pageSize);

	if(pdfReportPage != null) {
	    List<PDFReportVO> pdfList = pdfReportPage.getResult();
	    map.put("total", pdfReportPage.getTotal());
	    map.put("size", pageSize);
	    if(CollectionUtils.isNotEmpty(pdfList)) {
		map.put("rows", pdfList);
	    }
	}
	return map;
    }
    
    private Map<String, Object> findEbayResult(String userId , String page) {
   	Map<String, Object> map = new HashMap<String,Object>();
   	
   	int currentPage = 1;
   	if(StringUtils.isNotBlank(page)) {
   	    currentPage = Integer.parseInt(page);
   	}
   	
   	if(currentPage <0) {
   	    currentPage = 1;
   	}
   	int pageSize = 15;
   	Page<PDFReportVO> pdfReportPage = computingAmazonService.findPDFResultByPage(userId, currentPage, pageSize);

   	if(pdfReportPage != null) {
   	    List<PDFReportVO> pdfList = pdfReportPage.getResult();
   	    map.put("currentPage", pdfReportPage.getPageNum());
   	    map.put("totalPages", pdfReportPage.getPages());
   	    map.put("size", pageSize);
   	    if(CollectionUtils.isNotEmpty(pdfList)) {
   		map.put("pdfList", pdfList);
   	    }
   	}
   	return map;
       }
    
    
    private Integer findMinimumTimes() {
	Integer minimumTimes = 0;
	 SystemConfigVO systemConfigVO = new SystemConfigVO();
	 systemConfigVO.setConfigCode("user.tryTimes");
	 List<SystemConfigVO> systemConfigList =systemConfigMapper.findSystemConfigByCode(systemConfigVO);
	 if(CollectionUtils.isNotEmpty(systemConfigList)) {
	     SystemConfigVO ss =  systemConfigList.get(0);
	     if(ss.getConfigValue() != null) {
		 minimumTimes = Integer.parseInt(ss.getConfigValue());
	     }
	 }
	 return minimumTimes;
    }
    
}
