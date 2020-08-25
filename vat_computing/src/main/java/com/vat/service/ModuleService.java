package com.vat.service;

import java.util.List;

import com.vat.bean.ModuleInfo;


public interface ModuleService {
	/**
	 * 获取角色模块
	 * @param userId
	 * @return
	 */
	List<ModuleInfo> findModuleByUserId(String userId);
}
