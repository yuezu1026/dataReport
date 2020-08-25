package com.vat.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import com.vat.bean.EbayOrder;
import com.vat.dao.IOrderEbayDAO;



@Repository
public class OrderEbayDaoImpl implements IOrderEbayDAO {
    
    private final static Logger logger = LoggerFactory.getLogger(OrderEbayDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void batchOrderSave(List<EbayOrder> orderList) {
	logger.info("OrderEbayDaoImpl->batchOrderSave start...");

	SqlParameterSource[] beanSources = SqlParameterSourceUtils.createBatch(orderList.toArray());
	String sql = "INSERT INTO ebay_order_header(\r\n" + 
		"ebay_order_id,                         	\r\n" + 
		"ebay_user_id,                            \r\n" + 
		"sheet_name, 	                                \r\n" + 
		"sales_record_number, 	                        \r\n" + 
		"user_id, 	                                    \r\n" + 
		"buyer_fullname,                               \r\n" + 
		"buyer_phone_number,                          \r\n" + 
		"buyer_email, 	                                \r\n" + 
		"buyer_address_1, 	                            \r\n" + 
		"buyer_address_2, 	                            \r\n" + 
		"buyer_city,                                  \r\n" + 
		"buyer_state, 	                                \r\n" + 
		"buyer_zip,                                    \r\n" + 
		"buyer_country,                                \r\n" + 
		"order_id, 	                                \r\n" + 
		"item_id,                                     \r\n" + 
		"transaction_id,                             \r\n" + 
		"item_title, 	                                \r\n" + 
		"quantity, 	                                \r\n" + 
		"sale_price, 	                                \r\n" + 
		"shipping_and_handling, 	                    \r\n" + 
		"sales_tax, 	                                \r\n" + 
		"insurance, 	                                \r\n" + 
		"total_price, 	                                    \r\n" + 
		"payment_method, 	                            \r\n" + 
		"paypal_transaction_id, 	                    \r\n" + 
		"sale_date, 	                                \r\n" + 
		"checkout_date, 	                            \r\n" + 
		"paid_on_date, 	                            \r\n" + 
		"shipped_on_date, 	                            \r\n" + 
		"shipping_service, 	                        \r\n" + 
		"feedback_left, 	                            \r\n" + 
		"feedback_received, 	                        \r\n" + 
		"notes_to_yourself, 	                        \r\n" + 
		"custom_label, 	                            \r\n" + 
		"listed_on, 	                                \r\n" + 
		"sold_on, 	                                    \r\n" + 
		"private_notes, 	                            \r\n" + 
		"product_id_type, 	                            \r\n" + 
		"product_id_value,                             \r\n" + 
		"product_id_value_2, 	                        \r\n" + 
		"variation_details, 	                        \r\n" + 
		"product_reference_id, 	                    \r\n" + 
		"tracking_number,                             \r\n" + 
		"phone\r\n" + 
		") VALUES (:ebay_order_id,                         	\r\n" + 
		":ebay_user_id,                  \r\n" + 
		":sheet_name, 	                \r\n" + 
		":sales_record_number, 	        \r\n" + 
		":user_id, 	                    \r\n" + 
		":buyer_fullname,                \r\n" + 
		":buyer_phone_number,            \r\n" + 
		":buyer_email, 	                \r\n" + 
		":buyer_address_1, 	            \r\n" + 
		":buyer_address_2, 	            \r\n" + 
		":buyer_city,                     \r\n" + 
		":buyer_state, 	                \r\n" + 
		":buyer_zip,                     \r\n" + 
		":buyer_country,                 \r\n" + 
		":order_id, 	                    \r\n" + 
		":item_id,                       \r\n" + 
		":transaction_id,                \r\n" + 
		":item_title, 	                \r\n" + 
		":quantity, 	                    \r\n" + 
		":sale_price, 	                \r\n" + 
		":shipping_and_handling, 	    \r\n" + 
		":sales_tax, 	                \r\n" + 
		":insurance, 	                \r\n" + 
		":total_price, 	                \r\n" + 
		":payment_method, 	            \r\n" + 
		":paypal_transaction_id, 	    \r\n" + 
		":sale_date, 	                \r\n" + 
		":checkout_date, 	            \r\n" + 
		":paid_on_date, 	                \r\n" + 
		":shipped_on_date, 	            \r\n" + 
		":shipping_service, 	            \r\n" + 
		":feedback_left, 	            \r\n" + 
		":feedback_received, 	        \r\n" + 
		":notes_to_yourself, 	        \r\n" + 
		":custom_label, 	                \r\n" + 
		":listed_on, 	                \r\n" + 
		":sold_on, 	                    \r\n" + 
		":private_notes, 	            \r\n" + 
		":product_id_type, 	            \r\n" + 
		":product_id_value,              \r\n" + 
		":product_id_value_2, 	        \r\n" + 
		":variation_details, 	        \r\n" + 
		":product_reference_id, 	        \r\n" + 
		":tracking_number,               \r\n" + 
		":phone)";
	
	
	
	 /*sql = "INSERT INTO ebay_order_header(\r\n" + 
		"ebay_order_id,                         	\r\n" + 
		"ebay_user_id,                            \r\n" + 
		"sheet_name, 	                                \r\n" + 
		"sales_record_number, 	                        \r\n" + 
		"user_id, 	                                    \r\n" + 
		"buyer_fullname,                               \r\n" + 
		"buyer_phone_number,                          \r\n" + 
		"buyer_email, 	                                \r\n" + 
		"buyer_address_1, 	                            \r\n" + 
		"buyer_address_2, 	                            \r\n" + 
		"buyer_city                                  \r\n" + 
		
	
		
		
		
		"phone\r\n" + 
		") VALUES (:ebay_order_id,                         	\r\n" + 
		":ebay_user_id,                  \r\n" + 
		":sheet_name, 	                \r\n" + 
		":sales_record_number, 	        \r\n" + 
		":user_id, 	                    \r\n" + 
		":buyer_fullname,                \r\n" + 
		":buyer_phone_number,            \r\n" + 
		":buyer_email, 	                \r\n" + 
		":buyer_address_1, 	            \r\n" + 
		":buyer_address_2, 	            \r\n" + 
		":buyer_city                     \r\n" + 
	
	
		
		
		
		":phone)";*/
	
	namedParameterJdbcTemplate.batchUpdate(sql, beanSources);

    }

}
