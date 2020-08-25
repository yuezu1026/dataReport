package com.vat.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.vat.bean.GBOrder;
import com.vat.bean.AmazonOrder;

@SuppressWarnings("rawtypes")
@Mapper
public interface OrderGBMapper {

    Page<GBOrder> findOrderByPage(@Param("order") AmazonOrder order);

    public List<GBOrder> findActivityPeriod(@Param("userId") String userId);

    public List<String> findSaleDepartCountry(@Param("userId") String userId);

    public List<String> findsaleArrivalCountry(@Param("userId") String userId);

    public void deleteOrder(@Param("userId") String userId);

    public List<Map> sumATypeVatAmountBycurrency(Map param);

    public List<Map> sumBTypeVatAmountBycurrency(Map param);
    
    public List<Map> sumCTypeVatAmountBycurrency(Map param);


}
