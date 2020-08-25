package com.vat.service;

import com.github.pagehelper.Page;
import com.vat.bean.Order;

public interface OrderService {
    
    public Page<Order> findOrderByPage(String userId, int currentPage,int pageSize);
}
