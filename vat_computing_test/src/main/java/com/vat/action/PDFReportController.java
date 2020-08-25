package com.vat.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vat.bean.PDFReportVO;
import com.vat.bean.User;
import com.vat.service.ComputingService;
import com.vat.service.OrderService;

@RequestMapping(value = "/report")
@Controller
public class PDFReportController {

    private final static Logger logger = LoggerFactory.getLogger(PDFReportController.class);

    @Resource
    private OrderService orderService;

    @Resource
    @Qualifier("computingAmazonServiceImpl")
    private ComputingService computingAmazonService;

    @Resource
    @Qualifier("computingGBServiceImpl")
    private ComputingService computingGBService;

    @GetMapping(value = "generateReport")
    @Transactional(readOnly = true)
    public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws IOException {
	logger.info("get");
	User user = (User) request.getSession().getAttribute("user");
	if (user == null) {
	    response.sendRedirect(request.getContextPath() + "/index.html");
	    return null;
	} 

	Map<String,Object> map = new HashMap<String,Object>();
	return new ModelAndView("vat/computing", map);
    }

    @RequestMapping(value = "/download.do", method = RequestMethod.GET)
    public void Download(HttpServletRequest request, HttpServletResponse response) throws IOException {

	User user = (User) request.getSession().getAttribute("user");

	String userId = null;
	if (user != null) {
	    userId = user.getUserId();
	} else {
	    response.sendRedirect(request.getContextPath() + "/login.html");
	    return;
	}
	String pdfId = request.getParameter("pdfId");
	List<PDFReportVO> pdfList = computingAmazonService.findPDFResultById( pdfId,userId);
	if (CollectionUtils.isNotEmpty(pdfList)) {
	    PDFReportVO pdfReport = pdfList.get(0);
	    String filePath = pdfReport.getFilePath();
	    String fileName = pdfReport.getReportNo() + ".pdf";

	    response.setHeader("content-type", "application/octet-stream");
	    response.setContentType("application/octet-stream");
	    response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	    byte[] buff = new byte[1024];
	    BufferedInputStream bis = null;
	    OutputStream os = null;
	    FileInputStream fis = null;
	    try {
		os = response.getOutputStream();
		fis = new FileInputStream(new File(filePath));
		bis = new BufferedInputStream(fis);
		int i = bis.read(buff);
		while (i != -1) {
		    os.write(buff, 0, buff.length);
		    os.flush();
		    i = bis.read(buff);
		}
	    } catch (IOException e) {
		logger.error("error", e);
	    } finally {
		if (bis != null) {
		    try {
			bis.close();
		    } catch (IOException e) {
			logger.error("error", e);
		    }
		}
		if(fis != null) {
		    try {
			fis.close();
		    } catch (IOException e) {
			logger.error("error", e);
		    }
		}
	    }

	}

	logger.info("success");
    }

}
