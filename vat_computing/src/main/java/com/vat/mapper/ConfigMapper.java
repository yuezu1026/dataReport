package com.vat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.vat.bean.ParamConfigVO;


@Mapper
public interface ConfigMapper {

    public void insertConfig(@Param("paramConfigVO")ParamConfigVO paramConfigVO);
    public void updateConfig(@Param("paramConfigVO")ParamConfigVO paramConfigVO);
    List<ParamConfigVO> findConfig(@Param("paramConfigVO")ParamConfigVO paramConfigVO);
    
}