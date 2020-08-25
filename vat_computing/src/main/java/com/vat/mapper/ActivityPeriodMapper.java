package com.vat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.vat.bean.ActivityPeriod;


@Mapper
public interface ActivityPeriodMapper {

    public void insertActivityPeriod(@Param("activityPeriod") ActivityPeriod activityPeriod);
    
    public void deleteActivityPeriod(@Param("userId") String userId,@Param("dataType") String dataType);
    
    public List<ActivityPeriod> findActivityPeriod(@Param("userId") String userId,@Param("dataType") String dataType);
    
}