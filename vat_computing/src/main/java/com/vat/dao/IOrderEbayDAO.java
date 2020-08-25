package com.vat.dao;

import java.util.List;

import com.vat.bean.EbayOrder;

public interface IOrderEbayDAO {
    public void batchOrderSave(List<EbayOrder> list);
}
