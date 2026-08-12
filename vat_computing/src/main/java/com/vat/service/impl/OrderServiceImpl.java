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

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vat.bean.AmazonOrder;
import com.vat.mapper.OrderAmazonMapper;
import com.vat.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    
    @Resource
    private OrderAmazonMapper orderMapper;
    
    @Override
    public Page<AmazonOrder> findOrderByPage(String userId, int currentPage,int pageSize) {
	logger.info("OrderServiceImpl -> findOrderByPage");
	AmazonOrder order = new AmazonOrder();
	order.setUser_id(userId);
	 PageHelper.startPage(currentPage, pageSize);
	 Page<AmazonOrder> pageInfo  = orderMapper.findOrderByPage(order);
	 return pageInfo;
    }
}