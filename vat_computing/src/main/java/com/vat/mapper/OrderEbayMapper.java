package com.vat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.vat.bean.AmazonOrder;

@Mapper
public interface OrderEbayMapper {

    Page<AmazonOrder> findOrderByPage(@Param("order") AmazonOrder order);

    void deleteOrder(@Param("userId") String userId);
}
