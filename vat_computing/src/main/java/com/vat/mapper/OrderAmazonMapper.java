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
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.vat.bean.AmazonOrder;

@SuppressWarnings("rawtypes")
@Mapper
public interface OrderAmazonMapper {

    Page<AmazonOrder> findOrderByPage(@Param("order") AmazonOrder order);

    public List<AmazonOrder> findActivityPeriod(@Param("userId") String userId);

    public List<String> findSaleDepartCountry(@Param("userId") String userId);

    public List<String> findsaleArrivalCountry(@Param("userId") String userId);

    public void addOrder(@Param("order") AmazonOrder order);

    public void deleteOrder(@Param("userId") String userId);

    public List<Map> sumATypeVatAmountBycurrency(Map param);

    public List<Map> sumBTypeVatAmountBycurrency(Map param);
    
    public List<Map> sumCTypeVatAmountBycurrency(Map param);

}
