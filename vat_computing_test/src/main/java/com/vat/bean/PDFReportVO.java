package com.vat.bean;

import java.io.Serializable;

public class PDFReportVO implements Serializable {

    private static final long serialVersionUID = 3922913576662431375L;

    private String pdfId;
    private String userId;
    private String dataType;
    private String reportNo;
    private String needComputingCountry;
    private String filePath;
    private String createDate;


    public String getPdfId() {
	return pdfId;
    }

    public void setPdfId(String pdfId) {
	this.pdfId = pdfId;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getReportNo() {
	return reportNo;
    }

    public void setReportNo(String reportNo) {
	this.reportNo = reportNo;
    }

    public String getNeedComputingCountry() {
	return needComputingCountry;
    }

    public void setNeedComputingCountry(String needComputingCountry) {
	this.needComputingCountry = needComputingCountry;
    }

    public String getFilePath() {
	return filePath;
    }

    public void setFilePath(String filePath) {
	this.filePath = filePath;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
