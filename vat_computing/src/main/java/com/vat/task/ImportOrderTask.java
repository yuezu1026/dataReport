package com.vat.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vat.service.ImportService;

public class ImportOrderTask implements Runnable{
    
    private final static Logger logger = LoggerFactory.getLogger(ImportOrderTask.class);
    
    private ImportService importService;
    
    private String filePath;
    
    private String userId;
    
    private String excelName;
    
    public ImportOrderTask(ImportService importService, String excelName, String filePath, String userId) {
	this.importService = importService ;
	this.filePath = filePath;
	this.userId = userId;
	this.excelName = excelName;
    }
    
    @Override
    public void run() {
	try {
         importService.batchImport(filePath,excelName, userId);
	}catch(Exception e) {
	    logger.error("error",e);
	}
    }
}