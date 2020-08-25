package com.vat.service;

import java.util.List;

import com.vat.bean.ActivityPeriod;

public interface ActivityPeriodService {
    
    boolean insertActivityPeriod(ActivityPeriod activityPeriod) throws Exception;
    
    boolean insertActivityPeriod(String userId, List<String> activityPeriodList,String dataType) throws Exception;
    
    List<ActivityPeriod> findActivityPeriod(String userId,String dataType) throws Exception;
    
    boolean deleteActivityPeriod(String userId,String dataType) throws Exception;
}
