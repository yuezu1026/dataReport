package com.vat.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vat.bean.ImportBatchVO;
import com.vat.dao.IOrderEbayDAO;
import com.vat.mapper.ImportBatchMapper;
import com.vat.mapper.OrderEbayMapper;
import com.vat.service.ImportService;
import com.vat.util.UUIDUtils;
import com.vat.util.excel.ebay.ExcelReaderEbayUtil;

@Service
public class ImportEbayServiceImpl implements ImportService {

    private final static Logger logger = LoggerFactory.getLogger(ImportEbayServiceImpl.class);
    
    private static final String DATA_TYPE ="eBay";

    @Autowired
    private OrderEbayMapper orderEbayMapper;

    @Autowired
    private ImportBatchMapper importBatchMapper;
    
    @Autowired
    private IOrderEbayDAO orderEbayDAO;

    @Override
    public Integer batchImport(String filePath,String excelName, String userId) throws Exception {

	if (StringUtils.isEmpty(filePath)) {
	    return 0;
	}
	int importCount = 0;
	orderEbayMapper.deleteOrder(userId);//刪除原有数据
	ImportBatchVO importBatchVO = new ImportBatchVO();
	String batchId = UUIDUtils.getUUID();
	importBatchVO.setBatchId(batchId);
	importBatchVO.setUserId(userId);
	importBatchVO.setExcelName(excelName);
	importBatchVO.setDataType(DATA_TYPE);
	importBatchMapper.insertImportBatch(importBatchVO);
	try {
	     importCount = ExcelReaderEbayUtil.readExcel(filePath, userId, orderEbayDAO);
	    importBatchVO.setImportCount(String.valueOf(importCount));
	    importBatchMapper.finishBatch(importBatchVO);// 导入完成
	    File file = new File(filePath);
	    if(file.exists()) {
		file.delete();
	    }
	} catch (FileNotFoundException e) {
	    logger.error("error", e);
	} catch (IOException e) {
	    logger.error("error", e);
	}
	return importCount;
    }



}