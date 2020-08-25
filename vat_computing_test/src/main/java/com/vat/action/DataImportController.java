package com.vat.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.util.StringUtil;
import com.vat.bean.User;
import com.vat.config.UploadConfig;
import com.vat.service.ImportService;
import com.vat.service.UserService;
import com.vat.task.ImportOrderTask;

@Controller
public class DataImportController {

    private final static Logger logger = LoggerFactory.getLogger(DataImportController.class);

    @Autowired
    @Qualifier("importAmazonServiceImpl")
    private ImportService importAmazonService;
    
    @Autowired
    @Qualifier("importEbayServiceImpl")
    private ImportService importEbayService;
    
    @Autowired
    @Qualifier("importGBServiceImpl")
    private ImportService importGBService;

    @Autowired
    private UploadConfig uploadConfig;
    
    @Resource
    private UserService userService;


    @PostMapping("/importExcel.do")
    public ModelAndView importExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)  throws IOException {
	logger.info("importExcel start...");
	ImportService importService = null;
	String dataType = request.getParameter("dataType");
	if(StringUtil.isEmpty(dataType)){
	    dataType = "amazon";
	}
	if("amazon".equals(dataType)) {
	    importService = importAmazonService;
	}
	if("eBay".equals(dataType)) {
	    importService = importEbayService;
	}
	if("GB".equals(dataType)) {
	    importService = importGBService;
	}
	
	Map<String, Object> userMap = new HashMap<String, Object>();
	User user = (User) request.getSession().getAttribute("user");
	
	if(user == null) {
	    response.sendRedirect(request.getContextPath()+"/login.html");
	    return null;
	}
	
	String userId = null;
	String uploadRoot = uploadConfig.getUploadRoot();
	logger.info("uploadRoot: " + uploadRoot);
	if (user != null && !file.isEmpty()) {
	    userId = user.getUserId();
	    
	    user = userService.findUserById(userId);
	    
	    String passFlag = user.getPassFlag();
	    if ("Y".equals(passFlag)) {
		ExecutorService executor = null;
		try {
		    String filePath = uploadFile(file, userId, uploadRoot);
		    logger.info("filePath: " + filePath);
		    executor = Executors.newCachedThreadPool();
		    String excelName = file.getOriginalFilename();
		    ImportOrderTask task = new ImportOrderTask(importService,excelName, filePath, userId);
		     executor.submit(task);
		     userMap.put("flag", "1");
		} catch (Exception e) {
		    logger.error("error", e);
		} finally {
		    if (executor != null) {
			try {
			    executor.shutdown();
			} catch (Exception e) {
			    logger.error("error", e);
			}
		    }
		}
	    } else {
		logger.info("此用户无权限上传excel文件");
		userMap.put("flag", "2");
	    }
	}
	userMap.put("user", user);
	return new ModelAndView("vat/import_data", userMap);
    }

    private String uploadFile(MultipartFile file, String userId, String uploadPath) {

	if (!file.isEmpty()) {
	    String saveFileName = file.getOriginalFilename();
	    File saveFile = new File(uploadPath + userId + "/" + saveFileName);
	    if (!saveFile.getParentFile().exists()) {
		saveFile.getParentFile().mkdirs();
	    }
	    BufferedOutputStream out = null;
	    try {
		out = new BufferedOutputStream(new FileOutputStream(saveFile));
		out.write(file.getBytes());
		out.flush();
	    } catch (FileNotFoundException e) {
		logger.error("error", e);
	    } catch (IOException e) {
		logger.error("error", e);
	    } finally {
		if (out != null) {
		    try {
			out.close();
		    } catch (Exception e) {
			logger.error("error", e);
		    }
		}
	    }
	    return saveFile.getAbsolutePath();
	}
	return null;
    }

}
