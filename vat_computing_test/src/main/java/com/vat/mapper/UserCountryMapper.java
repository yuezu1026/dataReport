package com.vat.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings("rawtypes")
@Mapper
public interface UserCountryMapper {
    
    public List<Map> findUserTaxCountry(@Param("userId") String userId);
    
    public void saveUserCountry(@Param("userId") String userId, @Param("countryCode") String countryCode,
	    @Param("countryName") String countryName, @Param("taxFlag") String taxFlag);

    public void deleteUserCountry(@Param("userId") String userId);
    
}
