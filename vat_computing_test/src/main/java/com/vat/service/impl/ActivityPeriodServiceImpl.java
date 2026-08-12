/*
 * Copyright 2026 yuezu1026
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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