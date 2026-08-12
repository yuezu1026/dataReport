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
