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