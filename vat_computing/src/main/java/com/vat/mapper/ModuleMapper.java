package com.vat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.vat.bean.ModuleInfo;

@Mapper
public interface ModuleMapper {

	List<ModuleInfo> findModuleByUserId(@Param("userId") String userId);
}
