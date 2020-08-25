package com.vat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.vat.bean.ExchangeRateVO;


@Mapper
public interface ExchangeRateMapper {

    public List<ExchangeRateVO> findExchangeRate(@Param("period")String period);
    
}