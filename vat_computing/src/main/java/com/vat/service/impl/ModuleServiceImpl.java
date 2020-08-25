package com.vat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vat.bean.ModuleInfo;
import com.vat.mapper.ModuleMapper;
import com.vat.service.ModuleService;


@Service
public class ModuleServiceImpl implements ModuleService{
    
	@Autowired
	private ModuleMapper moduleMapper;

	/**
	 * 获取角色模块
	 * @param userId
	 * @return
	 */
	public List<ModuleInfo> findModuleByUserId(String userId) {
	    List<ModuleInfo> res  = moduleMapper.findModuleByUserId(userId);
		return res;
	}
}
