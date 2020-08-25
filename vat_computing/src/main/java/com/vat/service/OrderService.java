package com.vat.service;

import com.github.pagehelper.Page;
import com.vat.bean.AmazonOrder;

public interface OrderService {
    
    public Page<AmazonOrder> findOrderByPage(String userId, int currentPage,int pageSize);
}
