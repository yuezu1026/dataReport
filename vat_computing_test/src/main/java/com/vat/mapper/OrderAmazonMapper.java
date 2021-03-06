package com.vat.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.vat.bean.Order;

@SuppressWarnings("rawtypes")
@Mapper
public interface OrderAmazonMapper {

    Page<Order> findOrderByPage(@Param("order") Order order);

    public List<Order> findActivityPeriod(@Param("userId") String userId);

    public List<String> findSaleDepartCountry(@Param("userId") String userId);

    public List<String> findsaleArrivalCountry(@Param("userId") String userId);

    public void addOrder(@Param("order") Order order);

    public void deleteOrder(@Param("userId") String userId);

    public List<Map> sumATypeVatAmountBycurrency(Map param);

    public List<Map> sumBTypeVatAmountBycurrency(Map param);

}
