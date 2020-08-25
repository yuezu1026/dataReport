package com.vat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.vat.bean.Order;

@Mapper
public interface OrderEbayMapper {

    Page<Order> findOrderByPage(@Param("order") Order order);

    void deleteOrder(@Param("userId") String userId);
}
