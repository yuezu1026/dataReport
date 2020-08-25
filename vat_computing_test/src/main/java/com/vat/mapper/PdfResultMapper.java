package com.vat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.vat.bean.PDFReportVO;

@Mapper
public interface PdfResultMapper {
    
    Page<PDFReportVO> findPDFResultByPage(@Param("pdfReport") PDFReportVO pdfReportVO);
    
    List<PDFReportVO> findPDFResultById(@Param("pdfId") String pdfId,@Param("userId") String userId);
   
    public void savePDFResult(@Param("pdfReportVO") PDFReportVO pdfReportVO);

}
