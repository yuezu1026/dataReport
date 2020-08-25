package com.vat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.vat.bean.ParamConfigVO;
import com.vat.mapper.ConfigMapper;
import com.vat.service.ConfigService;
import com.vat.util.UUIDUtils;

@ComponentScan({ "com.vat.mapper" })
@Service("configServiceImpl")
public class ConfigServiceImpl implements ConfigService {

    private final static Logger logger = LoggerFactory.getLogger(ConfigServiceImpl.class);
   
    @Resource
    private ConfigMapper configMapper;

    @Override
    public void saveComputingConfig(String userId, String computingMethod) {
	logger.info("saveComputingConfig");
	ParamConfigVO paramConfigVO= new ParamConfigVO();
	paramConfigVO.setParamId(UUIDUtils.getUUID());
	paramConfigVO.setUserId(userId);
	paramConfigVO.setComputingMethod(computingMethod);
	List<ParamConfigVO> result = configMapper.findConfig(paramConfigVO);
	if(CollectionUtils.isNotEmpty(result)) {
	    configMapper.updateConfig(paramConfigVO);
	}else {
	    configMapper.insertConfig(paramConfigVO);
	}
    }

    @Override
    public ParamConfigVO findConfig(String userId) {

	ParamConfigVO paramConfigVO= new ParamConfigVO();
	paramConfigVO.setUserId(userId);
	
	List<ParamConfigVO> configList = configMapper.findConfig(paramConfigVO);
	if(CollectionUtils.isNotEmpty(configList)) {
	    return configList.get(0);
	}
	return null;
    }
}    

