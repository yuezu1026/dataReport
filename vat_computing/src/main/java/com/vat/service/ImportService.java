package com.vat.service;

public interface ImportService {
    Integer batchImport(String filePath,String excelName,String userId) throws Exception;
}
