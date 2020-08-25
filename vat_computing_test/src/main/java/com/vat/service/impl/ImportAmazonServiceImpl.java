package com.vat.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vat.bean.ImportBatchVO;
import com.vat.bean.Order;
import com.vat.dao.IOrderAmazonDAO;
import com.vat.mapper.ImportBatchMapper;
import com.vat.mapper.OrderAmazonMapper;
import com.vat.service.ActivityPeriodService;
import com.vat.service.ImportService;
import com.vat.util.UUIDUtils;
import com.vat.util.excel.amazon.ExcelReaderAmazonUtil;

@Service
public class ImportAmazonServiceImpl implements ImportService {

    private final static Logger logger = LoggerFactory.getLogger(ImportAmazonServiceImpl.class);
    
    private static final String DATA_TYPE ="Amazon";

    @Autowired
    private OrderAmazonMapper orderMapper;

    @Autowired
    private ActivityPeriodService activityPeriodService;

    @Autowired
    private ImportBatchMapper importBatchMapper;
    
    @Autowired
    private IOrderAmazonDAO orderDAO;

    @Override
    public Integer batchImport(String filePath,String excelName, String userId) throws Exception {

	if (StringUtils.isEmpty(filePath)) {
	    return 0;
	}
	int importCount = 0;
	orderMapper.deleteOrder(userId);//刪除原有数据
	ImportBatchVO importBatchVO = new ImportBatchVO();
	String batchId = UUIDUtils.getUUID();
	importBatchVO.setBatchId(batchId);
	importBatchVO.setUserId(userId);
	importBatchVO.setExcelName(excelName);
	importBatchVO.setDataType(DATA_TYPE);
	importBatchMapper.insertImportBatch(importBatchVO);
	
	try {
	     importCount = ExcelReaderAmazonUtil.readExcel(filePath, userId, orderDAO);
	    saveActivityPeroid(userId);
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

    private void saveActivityPeroid(String userId) throws Exception {
	activityPeriodService.deleteActivityPeriod(userId,DATA_TYPE);
	List<String> strActivityPeriodList = new ArrayList<String>();
	List<Order> activityPeroidList = orderMapper.findActivityPeriod(userId);
	if (CollectionUtils.isNotEmpty(activityPeroidList)) {
	    for (Order order : activityPeroidList) {
		strActivityPeriodList.add(order.getActivity_period());
	    }
	}
	activityPeriodService.insertActivityPeriod(userId, strActivityPeriodList,DATA_TYPE);
    }

}