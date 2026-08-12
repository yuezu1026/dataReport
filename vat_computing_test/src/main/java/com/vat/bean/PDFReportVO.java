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
