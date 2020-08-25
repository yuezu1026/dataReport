package com.vat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.vat.bean.ComputingResultVO;

@Mapper
public interface ComputingResultMapper {

    List<ComputingResultVO> findComputingResult(@Param("userId") String userId,@Param("period")String period,
	    @Param("needComputingCountry") String needComputingCountry,@Param("dataType") String dataType);

    public void saveComputingResult(@Param("computingResultVO") ComputingResultVO computingResultVO);

    public void deleteComputingResult(@Param("userId") String userId, @Param("period") String period,
	    @Param("needComputingCountry") String needComputingCountry,@Param("dataType") String dataType);

  

}
