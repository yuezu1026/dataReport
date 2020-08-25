package com.vat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.vat.bean.ImportBatchVO;


@Mapper
public interface ImportBatchMapper {

    public void insertImportBatch(@Param("batch") ImportBatchVO importBatchVO);
    
    public void finishBatch(@Param("batch") ImportBatchVO importBatchVO);
    
    public void deleteBatch(@Param("userId") String userId);
    
    public List<ImportBatchVO> findSingleImportBatch(@Param("batch") ImportBatchVO importBatchVO);
    
}