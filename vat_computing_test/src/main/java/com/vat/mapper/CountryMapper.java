package com.vat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.vat.bean.CountryVO;


@Mapper
public interface CountryMapper {

    public List<CountryVO> findTaxNoCountry();
    
    public List<CountryVO> findCountry(@Param("countryCode")String countryCode);
    
}