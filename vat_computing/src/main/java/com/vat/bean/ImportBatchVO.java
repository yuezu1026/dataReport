/*
 * Copyright 2026 yuezu1026
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
