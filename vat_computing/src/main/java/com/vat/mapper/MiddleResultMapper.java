package com.vat.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.vat.bean.ComputingMiddleResultVO;

@Mapper
public interface MiddleResultMapper {

    public List<ComputingMiddleResultVO> findMiddleResultList(@Param("userId") String userId,
	    @Param("period") String period, @Param("needComputingCountry") String needComputingCountry,@Param("dataType") String dataType);
    
    public void saveComputingMiddleResult(Map param);
    
    public void deleteComputingMiddleResult(@Param("userId") String userId, @Param("period") String period,
	    @Param("needComputingCountry") String needComputingCountry,@Param("dataType") String dataType);
    
    public List<Map> findMiddleTotalAmountResultList(@Param("userId") String userId, @Param("period") String period,
	    @Param("needComputingCountry") String needComputingCountry,@Param("dataType") String dataType);

}
