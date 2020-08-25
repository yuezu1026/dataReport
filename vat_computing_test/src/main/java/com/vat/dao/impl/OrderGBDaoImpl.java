package com.vat.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import com.vat.bean.GBOrder;
import com.vat.dao.IOrderGBDAO;

@Repository
public class OrderGBDaoImpl implements IOrderGBDAO {

    private final static Logger logger = LoggerFactory.getLogger(OrderGBDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void batchOrderSave(List<GBOrder> orderList) {
	logger.info("OrderGBDaoImpl-> batchOrderSave start...");

	SqlParameterSource[] beanSources = SqlParameterSourceUtils.createBatch(orderList.toArray());
	String sql = "INSERT INTO gb_order_header(gb_order_id, \r\n"
		+ "gb_user_id,	                                          \r\n"
		+ "sheet_name,	                                          \r\n"
		+ "inovice_no,	                                          \r\n"
		+ "inovice_date,	                                      \r\n"
		+ "unique_account_identifier,                             \r\n"
		+ "activity_period,	                                      \r\n"
		+ "marketplace,	                                          \r\n"
		+ "transaction_type,                                      \r\n"
		+ "transaction_event_id,                                  \r\n"
		+ "order_placed_date,                                     \r\n"
		+ "transaction_complete_date,	                          \r\n"
		+ "seller_sku,                                            \r\n"
		+ "item_description,                                      \r\n"
		+ "qty,                                                   \r\n"
		+ "item_weight,                                           \r\n"
		+ "total_activity_weight,                                 \r\n"
		+ "price_of_unit_item_amt_vat_incl,	                      \r\n"
		+ "price_of_items_amt_vat_incl,	                          \r\n"
		+ "promo_price_of_items_amt_vat_incl,	                  \r\n"
		+ "total_price_of_items_amt_vat_incl,	                  \r\n"
		+ "ship_charge_amt_vat_incl,	                          \r\n"
		+ "promo_ship_charge_amt_vat_incl,                        \r\n"
		+ "total_ship_charge_amt_vat_incl,                        \r\n"
		+ "promo_on_payment_amt_vat_incl,                         \r\n"
		+ "miscellaneous_charges,                                 \r\n"
		+ "total_activity_value_amt_vat_incl,                     \r\n"
		+ "transaction_currency_code,                            \r\n"
		+ "total_activity_value_amt_vat_incl_in_local_currency,	  \r\n"
		+ "local_currency,                                        \r\n"
		+ "sale_depature_city,                                    \r\n"
		+ "sale_depart_country,	                                  \r\n"
		+ "sale_departure_post_code,                              \r\n"
		+ "arrival_city,                                          \r\n"
		+ "arrival_country,	                                      \r\n"
		+ "arrival_post_code,                                     \r\n"
		+ "consignee,                                             \r\n" + "arrival_address\r\n"
		+ ") VALUES (:gb_order_id,\r\n" + ":gb_user_id,	                                          \r\n"
		+ ":sheet_name,	                                          \r\n"
		+ ":inovice_no,	                                          \r\n"
		+ ":inovice_date,	                                      \r\n"
		+ ":unique_account_identifier,                             \r\n"
		+ ":activity_period,	                                      \r\n"
		+ ":marketplace,	                                          \r\n"
		+ ":transaction_type,                                      \r\n"
		+ ":transaction_event_id,                                  \r\n"
		+ ":order_placed_date,                                     \r\n"
		+ ":transaction_complete_date,	                          \r\n"
		+ ":seller_sku,                                            \r\n"
		+ ":item_description,                                      \r\n"
		+ ":qty,                                                   \r\n"
		+ ":item_weight,                                           \r\n"
		+ ":total_activity_weight,                                 \r\n"
		+ ":price_of_unit_item_amt_vat_incl,	                      \r\n"
		+ ":price_of_items_amt_vat_incl,	                          \r\n"
		+ ":promo_price_of_items_amt_vat_incl,	                  \r\n"
		+ ":total_price_of_items_amt_vat_incl,	                  \r\n"
		+ ":ship_charge_amt_vat_incl,	                          \r\n"
		+ ":promo_ship_charge_amt_vat_incl,                        \r\n"
		+ ":total_ship_charge_amt_vat_incl,                        \r\n"
		+ ":promo_on_payment_amt_vat_incl,                         \r\n"
		+ ":miscellaneous_charges,                                 \r\n"
		+ ":total_activity_value_amt_vat_incl,                     \r\n"
		+ ":transaction_currency_code,                            \r\n"
		+ ":total_activity_value_amt_vat_incl_in_local_currency,	  \r\n"
		+ ":local_currency,                                        \r\n"
		+ ":sale_depature_city,                                    \r\n"
		+ ":sale_depart_country,	                                  \r\n"
		+ ":sale_departure_post_code,                              \r\n"
		+ ":arrival_city,                                          \r\n"
		+ ":arrival_country,	                                      \r\n"
		+ ":arrival_post_code,                                     \r\n"
		+ ":consignee,                                             \r\n" + ":arrival_address\r\n" + ")";

	namedParameterJdbcTemplate.batchUpdate(sql, beanSources);

    }

}
