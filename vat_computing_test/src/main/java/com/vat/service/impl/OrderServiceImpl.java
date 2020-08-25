package com.vat.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vat.bean.Order;
import com.vat.mapper.OrderAmazonMapper;
import com.vat.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    
    @Resource
    private OrderAmazonMapper orderMapper;
    
    @Override
    public Page<Order> findOrderByPage(String userId, int currentPage,int pageSize) {
	logger.info("OrderServiceImpl -> findOrderByPage");
	Order order = new Order();
	order.setUser_id(userId);
	 PageHelper.startPage(currentPage, pageSize);
	 Page<Order> pageInfo  = orderMapper.findOrderByPage(order);
	 return pageInfo;
    }
}