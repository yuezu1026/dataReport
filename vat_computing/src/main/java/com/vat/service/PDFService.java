package com.vat.service;

public interface PDFService {

    public String createPDF(String fontPath,String outPath,String privilegeFlag) throws Exception;
    public void imageWaterMark(String inputFile, String imageFile);
}
