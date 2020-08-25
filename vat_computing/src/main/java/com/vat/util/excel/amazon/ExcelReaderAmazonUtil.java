package com.vat.util.excel.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vat.bean.AmazonOrder;
import com.vat.dao.IOrderAmazonDAO;
import com.vat.util.BeanRefUtil;
import com.vat.util.UUIDUtils;

/**
 * @author y
 * @create 2018-01-19 0:13
 * @desc
 **/
public class ExcelReaderAmazonUtil {

    private final static Logger logger = LoggerFactory.getLogger(ExcelReaderAmazonUtil.class);

    // excel2003扩展名
    public static final String EXCEL03_EXTENSION = ".xls";
    // excel2007扩展名
    public static final String EXCEL07_EXTENSION = ".xlsx";

    /**
     * 每获取一条记录，即打印 在flume里每获取一条记录即发送，而不必缓存起来，可以大大减少内存的消耗，这里主要是针对flume读取大数据量excel来说的
     * 
     * @param sheetName
     * @param sheetIndex
     * @param curRow
     * @param cellList
     */
    public static void sendRows(String filePath, String sheetName, int sheetIndex, int curRow, List<String> cellList) {
	StringBuffer oneLineSb = new StringBuffer();
	oneLineSb.append(filePath);
	oneLineSb.append("--");
	oneLineSb.append("sheet" + sheetIndex);
	oneLineSb.append("::" + sheetName);// 加上sheet名
	oneLineSb.append("--");
	oneLineSb.append("row" + curRow);
	oneLineSb.append("::");
	for (String cell : cellList) {
	    oneLineSb.append(cell.trim());
	    oneLineSb.append("|");
	}
	String oneLine = oneLineSb.toString();
	if (oneLine.endsWith("|")) {
	    oneLine = oneLine.substring(0, oneLine.lastIndexOf("|"));
	} // 去除最后一个分隔符

	System.out.println(oneLine);
    }

    public static Map<String, Integer> readColNameMap(List<String> cellList) {
	if (cellList == null) {
	    return null;
	}
	String colName = null;
	Map<String, Integer> colNameMap = new HashMap<String, Integer>();

	for (int index = 0; index < cellList.size(); index++) {
	    colName = cellList.get(index);
	    if (StringUtils.isNotBlank(colName)) {
		colNameMap.put(colName.toLowerCase(), index);
	    }
	}
	return colNameMap;
    }

    public static List<AmazonOrder> readSaveSingleSheetData(IOrderAmazonDAO orderDAO, String userId,
	    Map<String, Integer> colNameMap, String sheetName, List<String> cellList) {
	List<AmazonOrder> orderList = new ArrayList<AmazonOrder>();

	AmazonOrder order = null;
	Map<String, String> colNameValueMap = null;

	String colName = null;
	Integer colIndex = null;
	String colValue = null;
	if (MapUtils.isEmpty(colNameMap)) {
	    logger.error("没有找到对应的数据列名");
	    return null;
	}

	order = new AmazonOrder();
	order.setOrder_id(UUIDUtils.getUUID());
	colNameValueMap = new HashMap<String, String>();

	for (Map.Entry<String, Integer> colNameIndex : colNameMap.entrySet()) {
	    colName = colNameIndex.getKey();
	    colIndex = colNameIndex.getValue();
	    for (int index = 0; index < cellList.size(); index++) {
		if (index == colIndex) {
		    colValue = cellList.get(index);
		    colNameValueMap.put(colName, colValue);
		    break;
		}
	    }
	}

	BeanRefUtil.setFieldValue(order, colNameValueMap);
	order.setUser_id(userId);
	order.setSheet_name(sheetName);
	orderList.add(order);

	return orderList;
    }

    public static int readExcel(String filePath, String userId, IOrderAmazonDAO orderDAO) throws Exception {
	int totalRows = 0;
	if (filePath.endsWith(EXCEL03_EXTENSION)) { // 处理excel2003文件
	    ExcelXlsReaderAmazon excelXlsReader = new ExcelXlsReaderAmazon(userId, orderDAO);
	    totalRows = excelXlsReader.process(filePath);

	    List<AmazonOrder> batchOrderList = excelXlsReader.getBatchOrderList();
	    if (CollectionUtils.isNotEmpty(batchOrderList)) {
		orderDAO.batchOrderSave(batchOrderList);
	    }

	} else if (filePath.endsWith(EXCEL07_EXTENSION)) {// 处理excel2007文件
	    ExcelXlsxReaderAmazon excelXlsxReader = new ExcelXlsxReaderAmazon(userId, orderDAO);
	    totalRows = excelXlsxReader.process(filePath);

	    List<AmazonOrder> batchOrderList = excelXlsxReader.getBatchOrderList();
	    if (CollectionUtils.isNotEmpty(batchOrderList)) {
		orderDAO.batchOrderSave(batchOrderList);
	    }

	} else {
	    throw new Exception("文件格式错误，fileName的扩展名只能是xls或xlsx。");
	}
	logger.info("excel count num：" + totalRows);
	return totalRows;
    }

    public static void main(String[] args) throws Exception {
	String path = "E:\\\\vat_computing\\\\upload\\\\temp\\\\5179ed5ad6194472b9d4f8aacad5caa2\\\\深圳市爱缇菲尔电子商务有限公司072018.xlsx";
	ExcelReaderAmazonUtil.readExcel(path, null, null);
    }
}