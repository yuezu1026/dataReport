package com.vat.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vat.bean.ActivityPeriod;
import com.vat.bean.ComputingMiddleResultVO;
import com.vat.bean.ComputingResultVO;
import com.vat.bean.CountryVO;
import com.vat.bean.ExchangeRateVO;
import com.vat.bean.ImportBatchVO;
import com.vat.bean.PDFReportVO;
import com.vat.mapper.ComputingResultMapper;
import com.vat.mapper.CountryMapper;
import com.vat.mapper.ExchangeRateMapper;
import com.vat.mapper.ImportBatchMapper;
import com.vat.mapper.MiddleResultMapper;
import com.vat.mapper.OrderAmazonMapper;
import com.vat.mapper.OrderEbayMapper;
import com.vat.mapper.OrderGBMapper;
import com.vat.mapper.PdfResultMapper;
import com.vat.mapper.UserCountryMapper;
import com.vat.service.ActivityPeriodService;
import com.vat.service.ComputingService;
import com.vat.util.DateUtil;
import com.vat.util.UUIDUtils;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class ComputingAmazonServiceImpl implements ComputingService {

    private final static Logger logger = LoggerFactory.getLogger(ComputingAmazonServiceImpl.class);
    
    private static final String DATA_TYPE = "Amazon";

    private final static Map MONTH_MAP;

    static {
	Map map = new HashMap();
	map.put("JAN", "01");
	map.put("FEB", "02");
	map.put("MAR", "03");
	map.put("APR", "04");
	map.put("MAY", "05");
	map.put("JUN", "06");
	map.put("JUL", "07");
	map.put("AUG", "08");
	map.put("SEP", "09");
	map.put("OCT", "10");
	map.put("NOV", "11");
	map.put("DEC", "12");
	
	map.put("一月", "01");
	map.put("二月", "02");
	map.put("三月", "03");
	map.put("四月", "04");
	map.put("五月", "05");
	map.put("六月", "06");
	map.put("七月", "07");
	map.put("八月", "08");
	map.put("九月", "09");
	map.put("十月", "10");
	map.put("十一月", "11");
	map.put("十二月", "12");
	
	MONTH_MAP = Collections.unmodifiableMap(map);
    }

    @Resource
    private OrderAmazonMapper orderAmazonMapper;
    
    @Resource
    private OrderGBMapper orderGBMapper;
    
    @Resource
    private OrderEbayMapper orderEbayMapper;

    @Resource
    private MiddleResultMapper middleResultMapper;

    @Resource
    private CountryMapper countryMapper;

    @Resource
    private ExchangeRateMapper exchangeRateMapper;

    @Resource
    private ActivityPeriodService activityPeriodService;

    @Resource
    private ImportBatchMapper importBatchMapper;
    
    @Resource
    private UserCountryMapper userCountryMapper;
    
    @Resource
    private ComputingResultMapper computingResultMapper;
    
    @Resource
    private PdfResultMapper pdfResultMapper;

    @Override
    public void saveUserCountry(String userId, String[] userSelectedCountryList) {

	List<CountryVO> taxNoCountryList = countryMapper.findTaxNoCountry();
	String countryCode = null;
	List<CountryVO> needSaveCountryList = new ArrayList<CountryVO>();
	if (CollectionUtils.isNotEmpty(taxNoCountryList)) {
	    for (CountryVO country : taxNoCountryList) {
		if (userSelectedCountryList != null && userSelectedCountryList.length > 0) {
		    for (String selectCountry : userSelectedCountryList) {
			countryCode = (String) country.getCountryCode();
			if (countryCode != null && countryCode.equals(selectCountry)) {
			    needSaveCountryList.add(country);
			}
		    }
		}
	    }
	}
	userCountryMapper.deleteUserCountry(userId);
	if (CollectionUtils.isNotEmpty(needSaveCountryList)) {
	    String countryName = null;
	    for (CountryVO country : needSaveCountryList) {
		countryCode = country.getCountryCode();
		countryName = country.getCountryName();
		userCountryMapper.saveUserCountry(userId, countryCode, countryName, "Y");
	    }
	}
    }

    @Override
    public void setCountryFlag(List<CountryVO> taxNoCountryList, List<Map> userTaxCountryList) {

	if (CollectionUtils.isNotEmpty(taxNoCountryList) && CollectionUtils.isNotEmpty(userTaxCountryList)) {
	    for (CountryVO country : taxNoCountryList) {
		for (Map userCountry : userTaxCountryList) {
		    if (country.getCountryCode().equals(userCountry.get("country_code"))) {
			country.setFlag("Y");
		    }
		}
	    }
	}
    }

    @Override
    public void computing(String userId, String[] periodList, String needComputingCountry) {

	if (periodList != null && periodList.length > 0) {
	    for (String period : periodList) {
		if (StringUtils.isNotBlank(period)) {
		    //// 清除所有中间数据
		    computingByPeriod(userId, period, needComputingCountry);
		    computingResultByPeriod(userId, period, needComputingCountry);
		} else {
		    logger.info("period is empty...");
		}
	    }
	}
	// 按计算国家产生报告
	logger.info("computing is finished...");

    }

    @Override
    public String generatePDFReport(String uploadRoot, String reportNo, String outPath, String userId,
	    String[] periodList, String needComputingCountry) throws Exception {
	if (StringUtils.isEmpty(outPath)) {
	    logger.info("outPath is emtpy.");
	}

	PDFServiceImpl pdfService = new PDFServiceImpl();

	String computingDate = DateUtil.getCurrentDate();

	pdfService.setReportNo(reportNo);
	pdfService.setComputingDate(computingDate);
	
	List<ComputingMiddleResultVO> computingMiddleResultList = findComputingMiddleResult(userId, periodList,
		needComputingCountry);
	if (CollectionUtils.isNotEmpty(computingMiddleResultList)) {
	    pdfService.setComputingMiddleResultList(computingMiddleResultList);
	}

	List<ComputingResultVO> computingResultList = findComputingResult(userId, periodList, needComputingCountry);
	if (CollectionUtils.isNotEmpty(computingResultList)) {
	    String userAcount = computingResultList.get(0).getUserAccount();
	    pdfService.setUserAcount(userAcount);
	    pdfService.setComputingResultList(computingResultList);

	    if (uploadRoot != null) {
		pdfService.createPDF(uploadRoot, outPath);
	    }
	}

	return outPath;
    }

    @Override
    public void computingByPeriod(String userId, String activityPeriod, String needComputingCountry) {

	middleResultMapper.deleteComputingMiddleResult(userId, activityPeriod, needComputingCountry,DATA_TYPE);

	computingResultMapper.deleteComputingResult(userId, activityPeriod, needComputingCountry,DATA_TYPE);

	Map<String, Object> paramMap = new HashMap<String, Object>();
	paramMap.put("userId", userId);
	paramMap.put("activityPeriod", activityPeriod);
	paramMap.put("needComputingCountry", needComputingCountry);
	List<Map> vatATypeList = orderAmazonMapper.sumATypeVatAmountBycurrency(paramMap);
	if (CollectionUtils.isNotEmpty(vatATypeList)) {
	    logger.info("vatList is not null");
	    Map<String, String> inputMap = null;
	    for (Map map : vatATypeList) {
		String currencyCode = (String) map.get("currencyCode");
		BigDecimal vatAmount = new BigDecimal(MapUtils.getDoubleValue(map, "vatAmount"));
		logger.info("currencyCode: " + currencyCode);
		logger.info("vatAmount: " + vatAmount);
		DecimalFormat df = new DecimalFormat("0.00");
		String allCostStr = df.format(vatAmount);
		logger.info("allCostStr: " + allCostStr);

		inputMap = new HashMap<String, String>();
		inputMap.put("computingId", UUIDUtils.getUUID());
		inputMap.put("userId", userId);
		inputMap.put("period", activityPeriod);
		inputMap.put("needComputingCountry", needComputingCountry);
		inputMap.put("computingType", "A");
		inputMap.put("currencyCode", currencyCode);
		inputMap.put("amount", allCostStr);
		middleResultMapper.saveComputingMiddleResult(inputMap);
	    }
	}

	List<Map> vatBTypeList = orderAmazonMapper.sumBTypeVatAmountBycurrency(paramMap);
	if (CollectionUtils.isNotEmpty(vatBTypeList)) {
	    logger.info("vatList is not null");
	    Map<String, String> inputMap = null;
	    for (Map map : vatBTypeList) {
		String currencyCode = (String) map.get("currencyCode");
		BigDecimal vatAmount = new BigDecimal(MapUtils.getDoubleValue(map, "vatAmount"));
		logger.info("currencyCode: " + currencyCode);
		logger.info("vatAmount: " + vatAmount);
		DecimalFormat df = new DecimalFormat("0.00");
		String allCostStr = df.format(vatAmount);
		logger.info("allCostStr: " + allCostStr);

		inputMap = new HashMap<String, String>();
		inputMap.put("computingId", UUIDUtils.getUUID());
		inputMap.put("userId", userId);
		inputMap.put("period", activityPeriod);
		inputMap.put("needComputingCountry", needComputingCountry);
		inputMap.put("computingType", "B");
		inputMap.put("currencyCode", currencyCode);
		inputMap.put("amount", allCostStr);
		middleResultMapper.saveComputingMiddleResult(inputMap);
	    }
	}
    }

    @Override
    public void computingResultByPeriod(String userId, String period, String needComputingCountry) {
	logger.info("computingResultByPeriod start");
	computingResultMapper.deleteComputingResult(userId, period, needComputingCountry,DATA_TYPE);
	CountryVO country = null;
	String needComputingCountrycurrencyCode = null;
	String standardVatRate = null;
	List<CountryVO> countryList = countryMapper.findCountry(needComputingCountry);
	if (CollectionUtils.isNotEmpty(countryList)) {
	    country = countryList.get(0);
	    needComputingCountrycurrencyCode = country.getCurrencyCode();
	    standardVatRate = country.getStandardVatRate();
	}

	ExchangeRateVO exchangeRateVO = null;
	String fromCurrencyCode = null;
	String toCurrencyCode = null;
	BigDecimal exchangeRate = null;
	List<ExchangeRateVO> exchangeRateList = exchangeRateMapper.findExchangeRate(period);
	if (CollectionUtils.isNotEmpty(exchangeRateList)) {
	    exchangeRateVO = exchangeRateList.get(0);
	    fromCurrencyCode = exchangeRateVO.getFromCurrencyCode();
	    toCurrencyCode = exchangeRateVO.getToCurrencyCode();
	    if (needComputingCountrycurrencyCode.equals(fromCurrencyCode)) {
		exchangeRate = new BigDecimal(1).divide(new BigDecimal(exchangeRateVO.getRate()), 10,
			BigDecimal.ROUND_HALF_DOWN);
	    }
	    if (needComputingCountrycurrencyCode.equals(toCurrencyCode)) {
		exchangeRate = new BigDecimal(exchangeRateVO.getRate());
	    }

	}
	List<Map> middleResultList = middleResultMapper.findMiddleTotalAmountResultList(userId, period, needComputingCountry,DATA_TYPE);
	BigDecimal includingTaxAmount = new BigDecimal(0);
	if (CollectionUtils.isNotEmpty(middleResultList)) {
	    String currencyCode = null;
	    Double total_amount = null;
	    for (Map middleResult : middleResultList) {
		// t.currency_code,sum(t.amount) total_amount
		currencyCode = (String) middleResult.get("currency_code");
		total_amount = MapUtils.getDouble(middleResult, "total_amount");

		if (needComputingCountrycurrencyCode.equals(currencyCode)) {
		    includingTaxAmount = includingTaxAmount.add(new BigDecimal(total_amount));
		} else {
		    // 需要汇率转换
		    includingTaxAmount = includingTaxAmount.add(new BigDecimal(total_amount).multiply(exchangeRate));
		}
	    }
	}
	// 计算税率
	BigDecimal excludingTaxAmount = null;
	// 税金=含税销售额/（1+19%） *19%
	// 1+ standardVatRate
	BigDecimal initValue = new BigDecimal(1);
	initValue = initValue.add(new BigDecimal(standardVatRate));
	BigDecimal vatAmount = includingTaxAmount.divide(initValue, 10, BigDecimal.ROUND_HALF_DOWN)
		.multiply(new BigDecimal(standardVatRate));

	DecimalFormat df = new DecimalFormat("0.00");
	String vatAmountDf = df.format(vatAmount);
	String includingTaxAmountDf = df.format(includingTaxAmount);
	excludingTaxAmount = includingTaxAmount.subtract(vatAmount);
	String excludingTaxAmountDf = df.format(excludingTaxAmount);

	ComputingResultVO computingVO = new ComputingResultVO();
	computingVO.setResultId(UUIDUtils.getUUID());
	computingVO.setUserId(userId);
	computingVO.setDataType(DATA_TYPE);;
	computingVO.setPeriod(period);
	computingVO.setPeriodDate(getSalePeriod(period));
	computingVO.setNeedComputingCountry(needComputingCountry);
	computingVO.setCurrencyCode(needComputingCountrycurrencyCode);
	computingVO.setIncludingTaxAmount(includingTaxAmountDf);
	computingVO.setExcludingTaxAmount(excludingTaxAmountDf);
	computingVO.setVatAmount(vatAmountDf);

	logger.info(userId + " : " + period + " : " + needComputingCountry + " : " + includingTaxAmountDf + " : "
		+ excludingTaxAmountDf + " : " + vatAmountDf);
	computingResultMapper.deleteComputingResult(userId, period, needComputingCountry,DATA_TYPE);
	computingResultMapper.saveComputingResult(computingVO);
    }

    private String getSalePeriod(String period) {
	if (StringUtils.isNotBlank(period) && period.indexOf("-") != -1) {
	    String key = period.substring(period.indexOf("-") + 1, period.length());
	    String year = period.substring(0, period.indexOf("-"));
	    String value = (String) MONTH_MAP.get(key);
	    logger.info("value: " + value);
	    return year + value;
	}
	return "0";
    }
    


    @Override
    public Map findAmazonBaseInfo(String userId) throws Exception {
	Map retMap = new HashMap();
	
	List<ActivityPeriod> activityPeriodList = activityPeriodService.findActivityPeriod(userId,DATA_TYPE);

	List<CountryVO> taxNoCountryList = countryMapper.findTaxNoCountry();
	List<Map> userTaxCountryList = userCountryMapper.findUserTaxCountry(userId);
	setCountryFlag(taxNoCountryList, userTaxCountryList);
	List<String> salesDepartCountryList = orderAmazonMapper.findSaleDepartCountry(userId);
	List<String> saleArrivalCountryList = orderAmazonMapper.findsaleArrivalCountry(userId);
	retMap.put("taxNoCountryList", taxNoCountryList);
	retMap.put("activityPeriodList", activityPeriodList);
	retMap.put("salesDepartCountryList", salesDepartCountryList);
	retMap.put("saleArrivalCountryList", saleArrivalCountryList);
	ImportBatchVO importBatchVO = new ImportBatchVO();
	importBatchVO.setUserId(userId);
	importBatchVO.setDataType(DATA_TYPE);
	List<ImportBatchVO> batchList = importBatchMapper.findSingleImportBatch(importBatchVO);
	ImportBatchVO batch = null;
	if (CollectionUtils.isNotEmpty(batchList)) {
	    batch = batchList.get(0);
	}
	retMap.put("currentBatch", batch);
	return retMap;

    }
    
    @Override
    public Map findGBBaseInfo(String userId) throws Exception {
	Map retMap = new HashMap();
	String dataType ="GB";
	List<ActivityPeriod> activityPeriodList = activityPeriodService.findActivityPeriod(userId,dataType);

	List<CountryVO> taxNoCountryList = countryMapper.findTaxNoCountry();
	List<Map> userTaxCountryList = userCountryMapper.findUserTaxCountry(userId);
	setCountryFlag(taxNoCountryList, userTaxCountryList);
	List<String> salesDepartCountryList = orderGBMapper.findSaleDepartCountry(userId);
	List<String> saleArrivalCountryList = orderGBMapper.findsaleArrivalCountry(userId);
	retMap.put("taxNoCountryList", taxNoCountryList);
	retMap.put("activityPeriodList", activityPeriodList);
	retMap.put("salesDepartCountryList", salesDepartCountryList);
	retMap.put("saleArrivalCountryList", saleArrivalCountryList);
	ImportBatchVO importBatchVO = new ImportBatchVO();
	importBatchVO.setUserId(userId);
	importBatchVO.setDataType(dataType);
	List<ImportBatchVO> batchList = importBatchMapper.findSingleImportBatch(importBatchVO);
	ImportBatchVO batch = null;
	if (CollectionUtils.isNotEmpty(batchList)) {
	    batch = batchList.get(0);
	}
	retMap.put("currentBatch", batch);
	return retMap;

    }

    @Override
    public List<ComputingResultVO> findComputingResult(String userId, String[] periodList, String needComputingCountry)
	    throws Exception {
	List<ComputingResultVO> reCcomputingResultList = new ArrayList<ComputingResultVO>();
	List<ComputingResultVO> computingResultList = null;
	for (String period : periodList) {
	    computingResultList = computingResultMapper.findComputingResult(userId, period, needComputingCountry,DATA_TYPE);
	    if (CollectionUtils.isNotEmpty(computingResultList)) {
		reCcomputingResultList.addAll(computingResultList);
	    }
	}
	return reCcomputingResultList;
    }

    @Override
    public List<ComputingMiddleResultVO> findComputingMiddleResult(String userId, String[] periodList,
	    String needComputingCountry) throws Exception {
	List<ComputingMiddleResultVO> reCcomputingResultList = new ArrayList<ComputingMiddleResultVO>();
	List<ComputingMiddleResultVO> computingResultList = null;
	for (String period : periodList) {
	    computingResultList = middleResultMapper.findMiddleResultList(userId, period, needComputingCountry,DATA_TYPE);
	    if (CollectionUtils.isNotEmpty(computingResultList)) {
		reCcomputingResultList.addAll(computingResultList);
	    }
	}
	return reCcomputingResultList;
    }

    @Override
    public void savePDFResult(PDFReportVO pdfReportVO) {
	pdfReportVO.setDataType(DATA_TYPE);
	pdfResultMapper.savePDFResult(pdfReportVO);

    }

    @Override
    public Page<PDFReportVO> findPDFResultByPage(String userId, int currentPage, int pageSize) {
	PDFReportVO pdfReportVO = new PDFReportVO();
	pdfReportVO.setUserId(userId);
	pdfReportVO.setDataType(DATA_TYPE);
	PageHelper.startPage(currentPage, pageSize);
	Page<PDFReportVO> pageInfo = pdfResultMapper.findPDFResultByPage(pdfReportVO);
	return pageInfo;
    }

    @Override
    public List<PDFReportVO> findPDFResultById(String pdfId, String userId) {

	List<PDFReportVO> pdfList = pdfResultMapper.findPDFResultById(pdfId, userId);
	return pdfList;
    }

}