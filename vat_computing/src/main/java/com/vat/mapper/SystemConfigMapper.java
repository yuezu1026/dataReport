package com.vat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.vat.bean.SystemConfigVO;


@Mapper
public interface SystemConfigMapper {

    public void insertSystemConfig(@Param("systemConfigVO")SystemConfigVO systemConfigVO);
    public void updateSystemConfig(@Param("systemConfigVO")SystemConfigVO systemConfigVO);
    List<SystemConfigVO> findSystemConfigByCode(@Param("systemConfigVO")SystemConfigVO systemConfigVO);
    List<SystemConfigVO> findSystemConfigById(@Param("systemConfigVO")SystemConfigVO systemConfigVO);
    
}