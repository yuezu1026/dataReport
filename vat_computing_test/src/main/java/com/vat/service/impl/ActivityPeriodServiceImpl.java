package com.vat.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.vat.bean.ActivityPeriod;
import com.vat.mapper.ActivityPeriodMapper;
import com.vat.service.ActivityPeriodService;
import com.vat.util.UUIDUtils;



@ComponentScan({"com.vat.mapper"})
@Service("activityPeriodService")
public class ActivityPeriodServiceImpl implements ActivityPeriodService {

    private final static Logger logger = LoggerFactory.getLogger(ActivityPeriodServiceImpl.class);
    
    @Resource
    private ActivityPeriodMapper activityPeriodMapper;
    
    
    
    @Override
    public boolean insertActivityPeriod(ActivityPeriod activityPeriod) throws Exception {
	logger.info("insertActivityPeriod start");
	activityPeriodMapper.insertActivityPeriod(activityPeriod);
	return true;
    }
    
    @Override
    public boolean insertActivityPeriod(String userId, List<String> activityPeriodList,String dataType) throws Exception{
	ActivityPeriod activityPeriodVO = null;
	String activityPeriodId  = null;
	for(String activityPeriod: activityPeriodList) {
	    activityPeriodVO = new ActivityPeriod();
	    activityPeriodId = UUIDUtils.getUUID();
	    activityPeriodVO.setActivityPeriodId(activityPeriodId);
	    activityPeriodVO.setUserId(userId);
	    activityPeriodVO.setDataType(dataType);
	    activityPeriodVO.setActivityPeriod(activityPeriod);
	    insertActivityPeriod(activityPeriodVO);
	}
	return true;
    }

    @Override
    public List<ActivityPeriod> findActivityPeriod(String userId,String dataType) throws Exception {
	List<ActivityPeriod> activityPeriod = activityPeriodMapper.findActivityPeriod(userId,dataType);
	return activityPeriod;
    }

    @Override
    public boolean deleteActivityPeriod(String userId,String dataType) throws Exception {
	activityPeriodMapper.deleteActivityPeriod(userId,dataType);
	return true;
    }

    

	

}