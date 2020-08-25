package com.vat.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.vat.bean.PDFReportVO;
import com.vat.bean.User;
import com.vat.config.UploadConfig;
import com.vat.mapper.CountryMapper;
import com.vat.mapper.ExchangeRateMapper;
import com.vat.mapper.OrderAmazonMapper;
import com.vat.service.ActivityPeriodService;
import com.vat.service.ComputingService;
import com.vat.service.ConfigService;
import com.vat.util.DateUtil;

@Controller
public class VatComputingController {

    private final static Logger logger = LoggerFactory.getLogger(VatComputingController.class);

    @Resource
    private ActivityPeriodService activityPeriodService;

    @Resource
    private OrderAmazonMapper orderAmazonMapper;

    @Resource
    private CountryMapper countryMapper;

    @Resource
    private ExchangeRateMapper exchangeRateMapper;

    @Resource
    @Qualifier("computingAmazonServiceImpl")
    private ComputingService computingAmazonService;

    @Resource
    @Qualifier("computingGBServiceImpl")
    private ComputingService computingGBService;

    @Autowired
    private UploadConfig uploadConfig;
    
    @Resource
    private ConfigService configService;

    @PostMapping("/vatComputing.do")
    public ModelAndView vatComputing(HttpServletRequest request, HttpServletResponse response) throws Exception {

	logger.info("vatComputing start");
	User user = (User) request.getSession().getAttribute("user");
	String userId = null;
	if (user != null) {
	    userId = user.getUserId();
	} else {
	    response.sendRedirect(request.getContextPath() + "/login.jsp");
	}
	String dataType = request.getParameter("dataType");
	if ("Amazon".equals(dataType)) {
	    return amazonComputing(request, userId);
	}else if ("GB".equals(dataType)) {
	    return gbComputing(request, userId);
	}else {
	    Map<String, Object> map = new HashMap<String, Object>();
	    return new ModelAndView("vat/result", map);
	}
    }

    public ModelAndView amazonComputing(HttpServletRequest request, String userId) throws Exception {

	String[] userSelectedCountryList = request.getParameterValues("taxNoCountry");
	// 保存用户勾选的国家
	if (userSelectedCountryList != null && userSelectedCountryList.length > 0) {
	    computingAmazonService.saveUserCountry(userId, userSelectedCountryList);
	}

	String needComputingCountry = request.getParameter("needComputingCountry");
	
	String taxRateType = request.getParameter("taxRate");

	String[] periodList = request.getParameterValues("period");

	Map<String, Object> map = new HashMap<String, Object>();

	if (periodList != null && periodList.length > 0) {

	    String downLoadRoot = uploadConfig.getDownloadRoot();
	    String reportNo = "CN-" + DateUtil.getDateNo();

	    String downloadPath = downLoadRoot + userId + "/" + DateUtil.getYyyyMMddDate() + "/" + needComputingCountry
		    + reportNo + ".pdf";

	    File saveFile = new File(downloadPath);
	    if (!saveFile.getParentFile().exists()) {
		saveFile.getParentFile().mkdirs();
	    }

	    // 计算增值税
	    computingAmazonService.computing(userId, periodList, needComputingCountry,taxRateType);
	    computingAmazonService.generatePDFReport(downLoadRoot, reportNo, downloadPath, userId, periodList,
		    needComputingCountry);
	    PDFReportVO pdfReportVO = new PDFReportVO();
	    pdfReportVO.setUserId(userId);
	    pdfReportVO.setDataType("Amazon");
	    pdfReportVO.setReportNo(reportNo);
	    pdfReportVO.setNeedComputingCountry(needComputingCountry);
	    pdfReportVO.setFilePath(downloadPath);
	    computingAmazonService.savePDFResult(pdfReportVO);
	} else {
	    map.put("success", "N");
	    return new ModelAndView("/vat/result", map);
	}
	map.put("success", "Y");

	int currentPage = 1;
	int pageSize = 15;
	Page<PDFReportVO> pdfReportPage = computingAmazonService.findPDFResultByPage(userId, currentPage, pageSize);
	if (pdfReportPage != null) {
	    List<PDFReportVO> pdfList = pdfReportPage.getResult();
	    map.put("currentPage", pdfReportPage.getPageNum());
	    map.put("totalPages", pdfReportPage.getPages());
	    map.put("size", pageSize);
	    if (CollectionUtils.isNotEmpty(pdfList)) {
		map.put("pdfList", pdfList);
	    }
	}
	map.put("dataType", "Amazon");
	return new ModelAndView("vat/result", map);
    }

    public ModelAndView gbComputing(HttpServletRequest request, String userId) throws Exception {

	String[] userSelectedCountryList = request.getParameterValues("taxNoCountry");
	// 保存用户勾选的国家
	if (userSelectedCountryList != null && userSelectedCountryList.length > 0) {
	    computingGBService.saveUserCountry(userId, userSelectedCountryList);
	}

	String needComputingCountry = request.getParameter("needComputingCountry");
	
	String taxRateType = request.getParameter("taxRate");

	String[] periodList = request.getParameterValues("period");

	Map<String, Object> map = new HashMap<String, Object>();

	if (periodList != null && periodList.length > 0) {

	    String downLoadRoot = uploadConfig.getDownloadRoot();
	    String reportNo = "CN-" + DateUtil.getDateNo();

	    String downloadPath = downLoadRoot + userId + "/" + DateUtil.getYyyyMMddDate() + "/" + needComputingCountry
		    + reportNo + ".pdf";

	    File saveFile = new File(downloadPath);
	    if (!saveFile.getParentFile().exists()) {
		saveFile.getParentFile().mkdirs();
	    }

	    // 计算增值税
	    computingGBService.computing(userId, periodList, needComputingCountry,taxRateType);
	    computingGBService.generatePDFReport(downLoadRoot, reportNo, downloadPath, userId, periodList,
		    needComputingCountry);
	    PDFReportVO pdfReportVO = new PDFReportVO();
	    pdfReportVO.setUserId(userId);
	    pdfReportVO.setReportNo(reportNo);
	    pdfReportVO.setNeedComputingCountry(needComputingCountry);
	    pdfReportVO.setFilePath(downloadPath);
	    computingGBService.savePDFResult(pdfReportVO);
	} else {
	    map.put("success", "N");
	    return new ModelAndView("/vat/result", map);
	}
	map.put("success", "Y");

	int currentPage = 1;
	int pageSize = 15;
	Page<PDFReportVO> pdfReportPage = computingGBService.findPDFResultByPage(userId, currentPage, pageSize);
	if (pdfReportPage != null) {
	    List<PDFReportVO> pdfList = pdfReportPage.getResult();
	    map.put("currentPage", pdfReportPage.getPageNum());
	    map.put("totalPages", pdfReportPage.getPages());
	    map.put("size", pageSize);
	    if (CollectionUtils.isNotEmpty(pdfList)) {
		map.put("pdfList", pdfList);
	    }
	}
	map.put("dataType", "GB");
	return new ModelAndView("vat/result", map);
    }

}
