package com.vat.dao;

import java.util.List;

import com.vat.bean.AmazonOrder;

public interface IOrderAmazonDAO {
    public void batchOrderSave(List<AmazonOrder> list);
}
 