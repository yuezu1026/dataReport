package com.vat.bean;

import java.io.Serializable;

public class ImportBatchVO  implements Serializable  {
    private static final long serialVersionUID = -1303041664719322077L;
    
    private String batchId;
    private String userId;
    private String excelName;
    private String dataType;
    private String importCount;
    private String startDate;
    private String endDate;
    private String isFinished;

    
    public String getBatchId() {
        return batchId;
    }
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getExcelName() {
        return excelName;
    }
    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }
    public String getImportCount() {
        return importCount;
    }
    public void setImportCount(String importCount) {
        this.importCount = importCount;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getIsFinished() {
        return isFinished;
    }
    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
