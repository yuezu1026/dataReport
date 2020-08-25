package com.vat.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.vat.bean.ComputingMiddleResultVO;
import com.vat.bean.ComputingResultVO;
import com.vat.bean.CountryVO;
import com.vat.bean.PDFReportVO;

@SuppressWarnings({"rawtypes"})
public interface ComputingService {
    
    void saveUserCountry(String userId, String[] userSelectedCountryList);

    void setCountryFlag(List<CountryVO> taxNoCountryList, List<Map> userTaxCountryList);

    void computing(String userId, String[] periodList, String needComputingCountry,String taxRateType);

    void computingByPeriod(String userId, String activityPeriod, String needComputingCountry);

    void computingResultByPeriod(String userId, String period, String needComputingCountry,String taxRateType);
    
    List<ComputingResultVO> findComputingResult(String userId,String[] periodList,String needComputingCountry) throws Exception;
    
    String generatePDFReport(String uploadRoot, String reportNo,String outPath,String userId,String[] periodList,String needComputingCountry) throws Exception;
    
    void savePDFResult(PDFReportVO pdfReportVO);
    
    public Page<PDFReportVO> findPDFResultByPage(String userId, int currentPage,int pageSize);
    
    public List<PDFReportVO> findPDFResultById(String userId, String pdfId);
    
    public List<ComputingMiddleResultVO> findComputingMiddleResult(String userId, String[] periodList,
	    String needComputingCountry) throws Exception;

    Map findAmazonBaseInfo(String userId) throws Exception;
    
    Map findGBBaseInfo(String userId) throws Exception;
    
    
}
