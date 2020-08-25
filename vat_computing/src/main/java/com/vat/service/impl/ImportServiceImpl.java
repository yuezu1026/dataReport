package com.vat.service.impl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.vat.bean.ImportBatchVO;
import com.vat.bean.AmazonOrder;
import com.vat.mapper.ImportBatchMapper;
import com.vat.mapper.OrderAmazonMapper;
import com.vat.service.ActivityPeriodService;
import com.vat.service.ImportService;
import com.vat.util.BeanRefUtil;
import com.vat.util.UUIDUtils;

//@Service
// @Transactional(readOnly = false)
public class ImportServiceImpl implements ImportService {

    private final static Logger logger = LoggerFactory.getLogger(ImportServiceImpl.class);
    
    private static final String DATA_TYPE ="Amazon";

    private final static Integer colNameRow = 0;

    private final static Integer dataStartRow = 1;

    @Autowired
    private OrderAmazonMapper orderMapper;

    @Autowired
    private ActivityPeriodService activityPeriodService;

    @Autowired
    private ImportBatchMapper importBatchMapper;

    @Override
    public Integer batchImport(String filePath,String excelName, String userId) throws Exception {

	if (StringUtils.isEmpty(filePath)) {
	    return 0;
	}
	orderMapper.deleteOrder(userId);// 刪除原有数据
	importBatchMapper.deleteBatch(userId);
	ImportBatchVO importBatchVO = new ImportBatchVO();
	String batchId = UUIDUtils.getUUID();
	importBatchVO.setBatchId(batchId);
	importBatchVO.setUserId(userId);
	importBatchMapper.insertImportBatch(importBatchVO);

	boolean isExcel2003 = true;
	if (filePath.matches("^.+\\.(?i)(xlsx)$")) {
	    isExcel2003 = false;
	}

	BufferedInputStream inputStream = null;
	Workbook workBook = null;
	try {
	    inputStream = new BufferedInputStream(new FileInputStream(filePath));

	    if (isExcel2003) {
		workBook = new HSSFWorkbook(inputStream);

	    } else {
		workBook = new SXSSFWorkbook(new XSSFWorkbook(inputStream), 1);
	    }

	    Sheet sheet = null;
	    Map<String, Integer> colNameMap = null;
	    for (int index = 0; index < workBook.getNumberOfSheets(); index++) {// 获取每个Sheet表
		sheet = workBook.getSheetAt(index);
		if (MapUtils.isEmpty(colNameMap)) {
		    colNameMap = readColNameMap(sheet);
		}
		readSaveSingleSheetData(userId, colNameMap, sheet);
	    }
	    saveActivityPeroid(userId);
	    importBatchMapper.finishBatch(importBatchVO);// 导入完成
	} catch (FileNotFoundException e) {
	    logger.error("error", e);
	} catch (IOException e) {
	    logger.error("error", e);
	} finally {
	    if (inputStream != null) {
		try {
		    inputStream.close();
		} catch (Exception e) {
		    logger.error("error", e);
		}
	    }
	    if (workBook != null) {

	    }
	}

	return 1;
    }

    private List<AmazonOrder> readSaveSingleSheetData(String userId, Map<String, Integer> colNameMap, Sheet sheet) {
	List<AmazonOrder> orderList = new ArrayList<AmazonOrder>();

	AmazonOrder order = null;
	Map<String, String> colNameValueMap = null;

	String colName = null;
	Integer colIndex = null;
	String colValue = null;
	Row row = null;
	if (MapUtils.isEmpty(colNameMap)) {
	    logger.error("没有找到对应的数据列名");
	    return null;
	}
	String sheetName = sheet.getSheetName();
	for (int rowIndex = dataStartRow; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
	    row = sheet.getRow(rowIndex);
	    if (row == null) {
		continue;
	    }
	    order = new AmazonOrder();
	    order.setOrder_id(UUIDUtils.getUUID());
	    colNameValueMap = new HashMap<String, String>();
	    for (Map.Entry<String, Integer> colNameIndex : colNameMap.entrySet()) {
		colName = colNameIndex.getKey();
		colIndex = colNameIndex.getValue();

		if (row.getCell(colIndex) != null) {
		    row.getCell(colIndex).setCellType(Cell.CELL_TYPE_STRING);
		    colValue = row.getCell(colIndex).getStringCellValue();
		    colNameValueMap.put(colName, colValue);
		}
	    }
	    BeanRefUtil.setFieldValue(order, colNameValueMap);
	    order.setUser_id(userId);
	    order.setSheet_name(sheetName);
	    orderList.add(order);
	}
	if (CollectionUtils.isNotEmpty(orderList)) {
	    for (AmazonOrder orderResord : orderList) {
		logger.info("orderResord = " + orderResord);
		orderMapper.addOrder(orderResord);
	    }
	}
	return orderList;
    }

    private Map<String, Integer> readColNameMap(Sheet sheet) {
	if (sheet == null) {
	    return null;
	}
	Row row = sheet.getRow(colNameRow);
	if (row == null) {
	    return null;
	}
	int columnNum = row.getPhysicalNumberOfCells();// 获取总列数
	String colName = null;
	Map<String, Integer> colNameMap = new HashMap<String, Integer>();
	for (int index = 0; index < columnNum; index++) {
	    if (row.getCell(index) != null) {
		colName = row.getCell(index).getStringCellValue();
		if (StringUtils.isNotBlank(colName)) {
		    colNameMap.put(colName.toLowerCase(), index);
		}
	    }
	}
	return colNameMap;
    }

    private void saveActivityPeroid(String userId) throws Exception {
	activityPeriodService.deleteActivityPeriod(userId,DATA_TYPE);
	List<String> strActivityPeriodList = new ArrayList<String>();
	List<AmazonOrder> activityPeroidList = orderMapper.findActivityPeriod(userId);
	if (CollectionUtils.isNotEmpty(activityPeroidList)) {
	    for (AmazonOrder order : activityPeroidList) {
		strActivityPeriodList.add(order.getActivity_period());
	    }
	}
	activityPeriodService.insertActivityPeriod(userId, strActivityPeriodList,DATA_TYPE);
    }

}