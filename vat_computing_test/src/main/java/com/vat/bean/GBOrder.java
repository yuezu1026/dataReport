package com.vat.bean;

import java.io.Serializable;

public class GBOrder implements Serializable {

    private static final long serialVersionUID = -6751675546063189332L;

    private String gb_order_id;
    private String gb_user_id;
    private String sheet_name;
    private String inovice_no;
    private String inovice_date;
    private String unique_account_identifier;
    private String activity_period;
    private String marketplace;
    private String transaction_type;
    private String transaction_event_id;
    private String order_placed_date;
    private String transaction_complete_date;
    private String seller_sku;
    private String item_description;
    private String qty;
    private String item_weight;
    private String total_activity_weight;
    private String price_of_unit_item_amt_vat_incl;
    private String price_of_items_amt_vat_incl;
    private String promo_price_of_items_amt_vat_incl;
    private String total_price_of_items_amt_vat_incl;
    private String ship_charge_amt_vat_incl;
    private String promo_ship_charge_amt_vat_incl;
    private String total_ship_charge_amt_vat_incl;
    private String promo_on_payment_amt_vat_incl;
    private String miscellaneous_charges;
    private String total_activity_value_amt_vat_incl;
    private String transaction_currency_code;
    private String total_activity_value_amt_vat_incl_in_local_currency;
    
    private String local_currency;
    private String sale_depature_city;
    private String sale_depart_country;
    private String sale_departure_post_code;
    private String arrival_city;
    private String arrival_country;
    private String arrival_post_code;
    private String consignee;
    private String arrival_address;
    

    public String getGb_order_id() {
        return gb_order_id;
    }
    public void setGb_order_id(String gb_order_id) {
        this.gb_order_id = gb_order_id;
    }
    public String getGb_user_id() {
        return gb_user_id;
    }
    public void setGb_user_id(String gb_user_id) {
        this.gb_user_id = gb_user_id;
    }
    public String getSheet_name() {
        return sheet_name;
    }
    public void setSheet_name(String sheet_name) {
        this.sheet_name = sheet_name;
    }

    
    public String getInovice_no() {
        return inovice_no;
    }
    public void setInovice_no(String inovice_no) {
        this.inovice_no = inovice_no;
    }
    public String getInovice_date() {
        return inovice_date;
    }
    public void setInovice_date(String inovice_date) {
        this.inovice_date = inovice_date;
    }
    public String getUnique_account_identifier() {
        return unique_account_identifier;
    }
    public void setUnique_account_identifier(String unique_account_identifier) {
        this.unique_account_identifier = unique_account_identifier;
    }
    public String getActivity_period() {
        return activity_period;
    }
    public void setActivity_period(String activity_period) {
        this.activity_period = activity_period;
    }
    public String getMarketplace() {
        return marketplace;
    }
    public void setMarketplace(String marketplace) {
        this.marketplace = marketplace;
    }
    public String getTransaction_type() {
        return transaction_type;
    }
    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }
    public String getTransaction_event_id() {
        return transaction_event_id;
    }
    public void setTransaction_event_id(String transaction_event_id) {
        this.transaction_event_id = transaction_event_id;
    }
    public String getOrder_placed_date() {
        return order_placed_date;
    }
    public void setOrder_placed_date(String order_placed_date) {
        this.order_placed_date = order_placed_date;
    }
    public String getTransaction_complete_date() {
        return transaction_complete_date;
    }
    public void setTransaction_complete_date(String transaction_complete_date) {
        this.transaction_complete_date = transaction_complete_date;
    }
    public String getSeller_sku() {
        return seller_sku;
    }
    public void setSeller_sku(String seller_sku) {
        this.seller_sku = seller_sku;
    }
    public String getItem_description() {
        return item_description;
    }
    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }
    public String getQty() {
        return qty;
    }
    public void setQty(String qty) {
        this.qty = qty;
    }
    public String getItem_weight() {
        return item_weight;
    }
    public void setItem_weight(String item_weight) {
        this.item_weight = item_weight;
    }
    public String getTotal_activity_weight() {
        return total_activity_weight;
    }
    public void setTotal_activity_weight(String total_activity_weight) {
        this.total_activity_weight = total_activity_weight;
    }
    public String getPrice_of_unit_item_amt_vat_incl() {
        return price_of_unit_item_amt_vat_incl;
    }
    public void setPrice_of_unit_item_amt_vat_incl(String price_of_unit_item_amt_vat_incl) {
        this.price_of_unit_item_amt_vat_incl = price_of_unit_item_amt_vat_incl;
    }
    public String getPrice_of_items_amt_vat_incl() {
        return price_of_items_amt_vat_incl;
    }
    public void setPrice_of_items_amt_vat_incl(String price_of_items_amt_vat_incl) {
        this.price_of_items_amt_vat_incl = price_of_items_amt_vat_incl;
    }
    public String getPromo_price_of_items_amt_vat_incl() {
        return promo_price_of_items_amt_vat_incl;
    }
    public void setPromo_price_of_items_amt_vat_incl(String promo_price_of_items_amt_vat_incl) {
        this.promo_price_of_items_amt_vat_incl = promo_price_of_items_amt_vat_incl;
    }
    public String getTotal_price_of_items_amt_vat_incl() {
        return total_price_of_items_amt_vat_incl;
    }
    public void setTotal_price_of_items_amt_vat_incl(String total_price_of_items_amt_vat_incl) {
        this.total_price_of_items_amt_vat_incl = total_price_of_items_amt_vat_incl;
    }
    public String getShip_charge_amt_vat_incl() {
        return ship_charge_amt_vat_incl;
    }
    public void setShip_charge_amt_vat_incl(String ship_charge_amt_vat_incl) {
        this.ship_charge_amt_vat_incl = ship_charge_amt_vat_incl;
    }
    public String getPromo_ship_charge_amt_vat_incl() {
        return promo_ship_charge_amt_vat_incl;
    }
    public void setPromo_ship_charge_amt_vat_incl(String promo_ship_charge_amt_vat_incl) {
        this.promo_ship_charge_amt_vat_incl = promo_ship_charge_amt_vat_incl;
    }
    public String getTotal_ship_charge_amt_vat_incl() {
        return total_ship_charge_amt_vat_incl;
    }
    public void setTotal_ship_charge_amt_vat_incl(String total_ship_charge_amt_vat_incl) {
        this.total_ship_charge_amt_vat_incl = total_ship_charge_amt_vat_incl;
    }
    public String getPromo_on_payment_amt_vat_incl() {
        return promo_on_payment_amt_vat_incl;
    }
    public void setPromo_on_payment_amt_vat_incl(String promo_on_payment_amt_vat_incl) {
        this.promo_on_payment_amt_vat_incl = promo_on_payment_amt_vat_incl;
    }
    public String getMiscellaneous_charges() {
        return miscellaneous_charges;
    }
    public void setMiscellaneous_charges(String miscellaneous_charges) {
        this.miscellaneous_charges = miscellaneous_charges;
    }
    public String getTotal_activity_value_amt_vat_incl() {
        return total_activity_value_amt_vat_incl;
    }
    public void setTotal_activity_value_amt_vat_incl(String total_activity_value_amt_vat_incl) {
        this.total_activity_value_amt_vat_incl = total_activity_value_amt_vat_incl;
    }
    public String getTransaction_currency_code() {
        return transaction_currency_code;
    }
    public void setTransaction_currency_code(String transaction_currency_code) {
        this.transaction_currency_code = transaction_currency_code;
    }
    public String getTotal_activity_value_amt_vat_incl_in_local_currency() {
        return total_activity_value_amt_vat_incl_in_local_currency;
    }
    public void setTotal_activity_value_amt_vat_incl_in_local_currency(
    	String total_activity_value_amt_vat_incl_in_local_currency) {
        this.total_activity_value_amt_vat_incl_in_local_currency = total_activity_value_amt_vat_incl_in_local_currency;
    }
    public String getLocal_currency() {
        return local_currency;
    }
    public void setLocal_currency(String local_currency) {
        this.local_currency = local_currency;
    }
    public String getSale_depature_city() {
        return sale_depature_city;
    }
    public void setSale_depature_city(String sale_depature_city) {
        this.sale_depature_city = sale_depature_city;
    }
    public String getSale_depart_country() {
        return sale_depart_country;
    }
    public void setSale_depart_country(String sale_depart_country) {
        this.sale_depart_country = sale_depart_country;
    }
    public String getSale_departure_post_code() {
        return sale_departure_post_code;
    }
    public void setSale_departure_post_code(String sale_departure_post_code) {
        this.sale_departure_post_code = sale_departure_post_code;
    }
    public String getArrival_city() {
        return arrival_city;
    }
    public void setArrival_city(String arrival_city) {
        this.arrival_city = arrival_city;
    }
    public String getArrival_country() {
        return arrival_country;
    }
    public void setArrival_country(String arrival_country) {
        this.arrival_country = arrival_country;
    }
    public String getArrival_post_code() {
        return arrival_post_code;
    }
    public void setArrival_post_code(String arrival_post_code) {
        this.arrival_post_code = arrival_post_code;
    }
    public String getConsignee() {
        return consignee;
    }
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }
    public String getArrival_address() {
        return arrival_address;
    }
    public void setArrival_address(String arrival_address) {
        this.arrival_address = arrival_address;
    }

}
