package com.vat.dao;

import java.util.List;

import com.vat.bean.GBOrder;

public interface IOrderGBDAO {
    public void batchOrderSave(List<GBOrder> list);
}
