package com.vat.dao;

import java.util.List;

import com.vat.bean.Order;

public interface IOrderAmazonDAO {
    public void batchOrderSave(List<Order> list);
}
 