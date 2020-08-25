package com.vat.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import com.vat.bean.AmazonOrder;
import com.vat.dao.IOrderAmazonDAO;
 
@Repository
public class OrderAmazonDaoImpl implements IOrderAmazonDAO {
    
    private final static Logger logger = LoggerFactory.getLogger(OrderAmazonDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override 
    public void batchOrderSave(List<AmazonOrder> orderList) {
	logger.info("batchOrderSave start...");

	SqlParameterSource[] beanSources = SqlParameterSourceUtils.createBatch(orderList.toArray());
	 String sql = "INSERT INTO order_header(order_id,\r\n" + 
		"      		user_id,\r\n" + 
		"      		sheet_name,\r\n" + 
		"			unique_account_identifier ,  \r\n" + 
		"			 activity_period,  \r\n" + 
		"			 sales_channel,  \r\n" + 
		"			 marketplace ,  \r\n" + 
		"			 transaction_type,  \r\n" + 
		"			 transaction_event_id,  \r\n" + 
		"			 activity_transaction_id ,  \r\n" + 
		"			 tax_calculation_date,  \r\n" + 
		"			 transaction_depart_date,  \r\n" + 
		"			 transaction_arrival_date,  \r\n" + 
		"			 transaction_complete_date,  \r\n" + 
		"			 seller_sku,  \r\n" + 
		"			 asin,  \r\n" + 
		"			 item_description ,  \r\n" + 
		"			 item_manufacture_country,  \r\n" + 
		"			 qty ,  \r\n" + 
		"			 item_weight ,  \r\n" + 
		"			 total_activity_weight,  \r\n" + 
		"			 cost_price_of_items,  \r\n" + 
		"			 price_of_items_amt_vat_excl ,  \r\n" + 
		"			 promo_price_of_items_amt_vat_excl,  \r\n" + 
		"			 total_price_of_items_amt_vat_excl,  \r\n" + 
		"			 ship_charge_amt_vat_excl ,  \r\n" + 
		"			 promo_ship_charge_amt_vat_excl ,  \r\n" + 
		"			 total_ship_charge_amt_vat_excl ,  \r\n" + 
		"			 gift_wrap_amt_vat_excl ,  \r\n" + 
		"			 promo_gift_wrap_amt_vat_excl ,  \r\n" + 
		"			 total_gift_wrap_amt_vat_excl,  \r\n" + 
		"			 total_activity_value_amt_vat_excl ,  \r\n" + 
		"			 price_of_items_vat_rate_percent,  \r\n" + 
		"			 price_of_items_vat_amt,  \r\n" + 
		"			 promo_price_of_items_vat_amt,  \r\n" + 
		"			 total_price_of_items_vat_amt ,  \r\n" + 
		"			 ship_charge_vat_rate_percent ,  \r\n" + 
		"			 ship_charge_vat_amt,  \r\n" + 
		"			 promo_ship_charge_vat_amt ,  \r\n" + 
		"			 total_ship_charge_vat_amt ,  \r\n" + 
		"			 gift_wrap_vat_rate_percent ,  \r\n" + 
		"			 gift_wrap_vat_amt,  \r\n" + 
		"			 promo_gift_wrap_vat_amt ,  \r\n" + 
		"			 total_gift_wrap_vat_amt ,  \r\n" + 
		"			 total_activity_value_vat_amt,  \r\n" + 
		"			 price_of_items_amt_vat_incl ,  \r\n" + 
		"			 promo_price_of_items_amt_vat_incl,  \r\n" + 
		"			 total_price_of_items_amt_vat_incl,  \r\n" + 
		"			 ship_charge_amt_vat_incl ,  \r\n" + 
		"			 promo_ship_charge_amt_vat_incl ,  \r\n" + 
		"			 total_ship_charge_amt_vat_incl,  \r\n" + 
		"			 gift_wrap_amt_vat_incl,  \r\n" + 
		"			 promo_gift_wrap_amt_vat_incl,  \r\n" + 
		"			 total_gift_wrap_amt_vat_incl ,  \r\n" + 
		"			 total_activity_value_amt_vat_incl,  \r\n" + 
		"			 transaction_currency_code,  \r\n" + 
		"			 commodity_code ,  \r\n" + 
		"			 statistical_code_depart ,  \r\n" + 
		"			 statistical_code_arrival,  \r\n" + 
		"			 commodity_code_supplementary_unit ,  \r\n" + 
		"			 item_qty_supplementary_unit  ,  \r\n" + 
		"			 total_activity_supplementary_unit ,  \r\n" + 
		"			 product_tax_code,  \r\n" + 
		"			 depature_city ,  \r\n" + 
		"			 departure_country ,  \r\n" + 
		"			 departure_post_code,  \r\n" + 
		"			 arrival_city ,  \r\n" + 
		"			 arrival_country ,  \r\n" + 
		"			 arrival_post_code ,  \r\n" + 
		"			 sale_depart_country ,  \r\n" + 
		"			 sale_arrival_country ,  \r\n" + 
		"			 transportation_mode ,  \r\n" + 
		"			 delivery_conditions ,  \r\n" + 
		"			 seller_depart_vat_number_country,  \r\n" + 
		"			 seller_depart_country_vat_number,  \r\n" + 
		"			 seller_arrival_vat_number_country,  \r\n" + 
		"			 seller_arrival_country_vat_number,  \r\n" + 
		"			 transaction_seller_vat_number_country,  \r\n" + 
		"			 transaction_seller_vat_number ,  \r\n" + 
		"			 buyer_vat_number_country,  \r\n" + 
		"			 buyer_vat_number,  \r\n" + 
		"			 vat_calculation_imputation_country,  \r\n" + 
		"			 taxable_jurisdiction,  \r\n" + 
		"			 taxable_jurisdiction_level,  \r\n" + 
		"			 vat_inv_number ,  \r\n" + 
		"			 vat_inv_converted_amt,  \r\n" + 
		"			 vat_inv_currency_code ,  \r\n" + 
		"			 vat_inv_exchange_rate ,  \r\n" + 
		"			 vat_inv_exchange_rate_date ,  \r\n" + 
		"			 export_outside_eu ,  \r\n" + 
		"			 invoice_url ,  \r\n" + 
		"			 buyer_name ,  \r\n" + 
		"			 arrival_address) VALUES (:order_id,\r\n" + 
		"      		:user_id,\r\n" + 
		"      		:sheet_name,\r\n" + 
		"			:unique_account_identifier ,  \r\n" + 
		"			 :activity_period,  \r\n" + 
		"			 :sales_channel,  \r\n" + 
		"			 :marketplace ,  \r\n" + 
		"			 :transaction_type,  \r\n" + 
		"			 :transaction_event_id,  \r\n" + 
		"			 :activity_transaction_id ,  \r\n" + 
		"			 :tax_calculation_date,  \r\n" + 
		"			 :transaction_depart_date,  \r\n" + 
		"			 :transaction_arrival_date,  \r\n" + 
		"			 :transaction_complete_date,  \r\n" + 
		"			 :seller_sku,  \r\n" + 
		"			 :asin,  \r\n" + 
		"			 :item_description ,  \r\n" + 
		"			 :item_manufacture_country,  \r\n" + 
		"			:qty ,  \r\n" + 
		"			 :item_weight ,  \r\n" + 
		"			 :total_activity_weight,  \r\n" + 
		"			 :cost_price_of_items,  \r\n" + 
		"			 :price_of_items_amt_vat_excl ,  \r\n" + 
		"			 :promo_price_of_items_amt_vat_excl,  \r\n" + 
		"			 :total_price_of_items_amt_vat_excl,  \r\n" + 
		"			 :ship_charge_amt_vat_excl ,  \r\n" + 
		"			 :promo_ship_charge_amt_vat_excl ,  \r\n" + 
		"			 :total_ship_charge_amt_vat_excl ,  \r\n" + 
		"			 :gift_wrap_amt_vat_excl ,  \r\n" + 
		"			 :promo_gift_wrap_amt_vat_excl ,  \r\n" + 
		"			 :total_gift_wrap_amt_vat_excl,  \r\n" + 
		"			 :total_activity_value_amt_vat_excl ,  \r\n" + 
		"			 :price_of_items_vat_rate_percent,  \r\n" + 
		"			 :price_of_items_vat_amt,  \r\n" + 
		"			 :promo_price_of_items_vat_amt,  \r\n" + 
		"			 :total_price_of_items_vat_amt ,  \r\n" + 
		"			 :ship_charge_vat_rate_percent ,  \r\n" + 
		"			 :ship_charge_vat_amt,  \r\n" + 
		"			 :promo_ship_charge_vat_amt ,  \r\n" + 
		"			 :total_ship_charge_vat_amt ,  \r\n" + 
		"			 :gift_wrap_vat_rate_percent ,  \r\n" + 
		"			 :gift_wrap_vat_amt,  \r\n" + 
		"			 :promo_gift_wrap_vat_amt ,  \r\n" + 
		"			 :total_gift_wrap_vat_amt ,  \r\n" + 
		"			 :total_activity_value_vat_amt,  \r\n" + 
		"			 :price_of_items_amt_vat_incl ,  \r\n" + 
		"			 :promo_price_of_items_amt_vat_incl,  \r\n" + 
		"			 :total_price_of_items_amt_vat_incl,  \r\n" + 
		"			 :ship_charge_amt_vat_incl ,  \r\n" + 
		"			 :promo_ship_charge_amt_vat_incl ,  \r\n" + 
		"			 :total_ship_charge_amt_vat_incl,  \r\n" + 
		"			 :gift_wrap_amt_vat_incl,  \r\n" + 
		"			 :promo_gift_wrap_amt_vat_incl,  \r\n" + 
		"			 :total_gift_wrap_amt_vat_incl ,  \r\n" + 
		"			 :total_activity_value_amt_vat_incl,  \r\n" + 
		"			 :transaction_currency_code,  \r\n" + 
		"			 :commodity_code ,  \r\n" + 
		"			 :statistical_code_depart ,  \r\n" + 
		"			 :statistical_code_arrival,  \r\n" + 
		"			 :commodity_code_supplementary_unit ,  \r\n" + 
		"			 :item_qty_supplementary_unit  ,  \r\n" + 
		"			 :total_activity_supplementary_unit ,  \r\n" + 
		"			 :product_tax_code,  \r\n" + 
		"			 :depature_city ,  \r\n" + 
		"			 :departure_country ,  \r\n" + 
		"			 :departure_post_code,  \r\n" + 
		"			 :arrival_city ,  \r\n" + 
		"			 :arrival_country ,  \r\n" + 
		"			 :arrival_post_code ,  \r\n" + 
		"			 :sale_depart_country ,  \r\n" + 
		"			 :sale_arrival_country ,  \r\n" + 
		"			 :transportation_mode ,  \r\n" + 
		"			 :delivery_conditions ,  \r\n" + 
		"			 :seller_depart_vat_number_country,  \r\n" + 
		"			 :seller_depart_country_vat_number,  \r\n" + 
		"			 :seller_arrival_vat_number_country,  \r\n" + 
		"			 :seller_arrival_country_vat_number,  \r\n" + 
		"			 :transaction_seller_vat_number_country,  \r\n" + 
		"			 :transaction_seller_vat_number ,  \r\n" + 
		"			 :buyer_vat_number_country,  \r\n" + 
		"			 :buyer_vat_number,  \r\n" + 
		"			 :vat_calculation_imputation_country,  \r\n" + 
		"			 :taxable_jurisdiction,  \r\n" + 
		"			 :taxable_jurisdiction_level,  \r\n" + 
		"			 :vat_inv_number ,  \r\n" + 
		"			 :vat_inv_converted_amt,  \r\n" + 
		"			 :vat_inv_currency_code ,  \r\n" + 
		"			 :vat_inv_exchange_rate ,  \r\n" + 
		"			 :vat_inv_exchange_rate_date ,  \r\n" + 
		"			 :export_outside_eu ,  \r\n" + 
		"			 :invoice_url ,  \r\n" + 
		"			 :buyer_name ,  \r\n" + 
		"			 :arrival_address)";
	namedParameterJdbcTemplate.batchUpdate(sql, beanSources);

    }



}
