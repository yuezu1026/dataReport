package com.vat.util.excel.gb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vat.bean.GBOrder;
import com.vat.dao.IOrderGBDAO;
import com.vat.util.BeanRefUtil;
import com.vat.util.UUIDUtils;

/**
 * @author y
 * @create 2018-01-19 0:13
 * @desc
 **/
public class ExcelReaderGBUtil {

    private final static Logger logger = LoggerFactory.getLogger(ExcelReaderGBUtil.class);

    // excel2003扩展名
    public static final String EXCEL03_EXTENSION = ".xls";
    // excel2007扩展名
    public static final String EXCEL07_EXTENSION = ".xlsx";
    
    private final static Map MONTH_MAP;

    static {
	Map map = new HashMap();

	map.put("一月", "JAN");
	map.put("二月", "FEB");
	map.put("三月", "MAR");
	map.put("四月", "APR");
	map.put("五月", "MAY");
	map.put("六月", "JUN");
	map.put("七月", "JUL");
	map.put("八月", "AUG");
	map.put("九月", "SEP");
	map.put("十月", "OCT");
	map.put("十一月", "NOV");
	map.put("十二月", "DEC");
	
	MONTH_MAP = Collections.unmodifiableMap(map);
    }

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
		colName = colName.replaceAll(" ", "_");
		logger.info(colName);
		colNameMap.put(colName.toLowerCase(), index);
	    }
	}
	return colNameMap;
    }

    public static List<GBOrder> readSaveSingleSheetData(IOrderGBDAO orderDAO, String userId,
	    Map<String, Integer> colNameMap, String sheetName, List<String> cellList) {
	List<GBOrder> orderList = new ArrayList<GBOrder>();

	GBOrder order = null;
	Map<String, String> colNameValueMap = null;

	String colName = null;
	Integer colIndex = null;
	String colValue = null;
	if (MapUtils.isEmpty(colNameMap)) {
	    logger.error("没有找到对应的数据列名");
	    return null;
	}

	order = new GBOrder();
	order.setGb_order_id(UUIDUtils.getUUID());
	colNameValueMap = new HashMap<String, String>();

	for (Map.Entry<String, Integer> colNameIndex : colNameMap.entrySet()) {
	    colName = colNameIndex.getKey();
	    colIndex = colNameIndex.getValue();
	    for (int index = 0; index < cellList.size(); index++) {
		if (index == colIndex) {
		    colValue = cellList.get(index);
		    colValue = getSalePeriod(colValue);
		    colNameValueMap.put(colName, colValue);
		    break;
		}
	    }
	}

	BeanRefUtil.setFieldValue(order, colNameValueMap);
	order.setGb_user_id(userId);
	order.setSheet_name(sheetName);
	orderList.add(order);

	return orderList;
    }
    
    private static String getSalePeriod(String period) {
  	if (StringUtils.isNotBlank(period) && period.indexOf("-") != -1) {
  	    String key = period.substring(period.indexOf("-") + 1, period.length());
  	    String year = period.substring(0, period.indexOf("-"));
  	    String value = (String) MONTH_MAP.get(key);
  	    if(StringUtils.isNotEmpty(value)){
  		logger.info("value: " +"-"+ value);
  		return year +"-"+ value;
  	    }
  	}
  	return period;
      }

    public static int readExcel(String filePath, String userId, IOrderGBDAO orderDAO) throws Exception {
	int totalRows = 0;
	if (filePath.endsWith(EXCEL03_EXTENSION)) { // 处理excel2003文件
	    ExcelXlsReaderGB excelXlsReader = new ExcelXlsReaderGB(userId, orderDAO);
	    totalRows = excelXlsReader.process(filePath);

	    List<GBOrder> batchOrderList = excelXlsReader.getBatchOrderList();
	    if (CollectionUtils.isNotEmpty(batchOrderList)) {
		orderDAO.batchOrderSave(batchOrderList);
	    }

	} else if (filePath.endsWith(EXCEL07_EXTENSION)) {// 处理excel2007文件
	    ExcelXlsxReaderGB excelXlsxReader = new ExcelXlsxReaderGB(userId, orderDAO);
	    totalRows = excelXlsxReader.process(filePath);

	    List<GBOrder> batchOrderList = excelXlsxReader.getBatchOrderList();
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
	ExcelReaderGBUtil.readExcel(path, null, null);
    }
}