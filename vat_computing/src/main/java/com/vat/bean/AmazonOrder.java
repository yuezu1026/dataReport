package com.vat.bean;

import java.io.Serializable;

public class AmazonOrder implements Serializable {

    private static final long serialVersionUID = 554743561558747781L;

    private String order_id;
    private String user_id;
    private String sheet_name;
    private String unique_account_identifier;
    private String activity_period;
    private String sales_channel;
    private String marketplace;
    private String transaction_type;
    private String transaction_event_id;
    private String activity_transaction_id;
    private String tax_calculation_date;
    private String transaction_depart_date;
    private String transaction_arrival_date;
    private String transaction_complete_date;
    private String seller_sku;
    private String asin;
    private String item_description;
    private String item_manufacture_country;
    private String qty;
    private String item_weight;
    private String total_activity_weight;
    private String cost_price_of_items;
    private String price_of_items_amt_vat_excl;
    private String promo_price_of_items_amt_vat_excl;
    private String total_price_of_items_amt_vat_excl;
    private String ship_charge_amt_vat_excl;
    private String promo_ship_charge_amt_vat_excl;
    private String total_ship_charge_amt_vat_excl;
    private String gift_wrap_amt_vat_excl;
    private String promo_gift_wrap_amt_vat_excl;
    private String total_gift_wrap_amt_vat_excl;
    private String total_activity_value_amt_vat_excl;
    private String price_of_items_vat_rate_percent;
    private String price_of_items_vat_amt;
    private String promo_price_of_items_vat_amt;
    private String total_price_of_items_vat_amt;
    private String ship_charge_vat_rate_percent;
    private String ship_charge_vat_amt;
    private String promo_ship_charge_vat_amt;
    private String total_ship_charge_vat_amt;
    private String gift_wrap_vat_rate_percent;
    private String gift_wrap_vat_amt;
    private String promo_gift_wrap_vat_amt;
    private String total_gift_wrap_vat_amt;
    private String total_activity_value_vat_amt;
    private String price_of_items_amt_vat_incl;
    private String promo_price_of_items_amt_vat_incl;
    private String total_price_of_items_amt_vat_incl;
    private String ship_charge_amt_vat_incl;
    private String promo_ship_charge_amt_vat_incl;
    private String total_ship_charge_amt_vat_incl;
    private String gift_wrap_amt_vat_incl;
    private String promo_gift_wrap_amt_vat_incl;
    private String total_gift_wrap_amt_vat_incl;
    private String total_activity_value_amt_vat_incl;
    private String transaction_currency_code;
    private String commodity_code;
    private String statistical_code_depart;
    private String statistical_code_arrival;
    private String commodity_code_supplementary_unit;
    private String item_qty_supplementary_unit;
    private String total_activity_supplementary_unit;
    private String product_tax_code;
    private String depature_city;
    private String departure_country;
    private String departure_post_code;
    private String arrival_city;
    private String arrival_country;
    private String arrival_post_code;
    private String sale_depart_country;
    private String sale_arrival_country;
    private String transportation_mode;
    private String delivery_conditions;
    private String seller_depart_vat_number_country;
    private String seller_depart_country_vat_number;
    private String seller_arrival_vat_number_country;
    private String seller_arrival_country_vat_number;
    private String transaction_seller_vat_number_country;
    private String transaction_seller_vat_number;
    private String buyer_vat_number_country;
    private String buyer_vat_number;
    private String vat_calculation_imputation_country;
    private String taxable_jurisdiction;
    private String taxable_jurisdiction_level;
    private String vat_inv_number;
    private String vat_inv_converted_amt;
    private String vat_inv_currency_code;
    private String vat_inv_exchange_rate;
    private String vat_inv_exchange_rate_date;
    private String export_outside_eu;
    private String invoice_url;
    private String buyer_name;
    private String arrival_address;
    
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
    public String getSales_channel() {
        return sales_channel;
    }
    public void setSales_channel(String sales_channel) {
        this.sales_channel = sales_channel;
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
    public String getActivity_transaction_id() {
        return activity_transaction_id;
    }
    public void setActivity_transaction_id(String activity_transaction_id) {
        this.activity_transaction_id = activity_transaction_id;
    }
    public String getTax_calculation_date() {
        return tax_calculation_date;
    }
    public void setTax_calculation_date(String tax_calculation_date) {
        this.tax_calculation_date = tax_calculation_date;
    }
    public String getTransaction_depart_date() {
        return transaction_depart_date;
    }
    public void setTransaction_depart_date(String transaction_depart_date) {
        this.transaction_depart_date = transaction_depart_date;
    }
    public String getTransaction_arrival_date() {
        return transaction_arrival_date;
    }
    public void setTransaction_arrival_date(String transaction_arrival_date) {
        this.transaction_arrival_date = transaction_arrival_date;
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
    public String getAsin() {
        return asin;
    }
    public void setAsin(String asin) {
        this.asin = asin;
    }
    public String getItem_description() {
        return item_description;
    }
    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }
    public String getItem_manufacture_country() {
        return item_manufacture_country;
    }
    public void setItem_manufacture_country(String item_manufacture_country) {
        this.item_manufacture_country = item_manufacture_country;
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
    public String getCost_price_of_items() {
        return cost_price_of_items;
    }
    public void setCost_price_of_items(String cost_price_of_items) {
        this.cost_price_of_items = cost_price_of_items;
    }
    public String getPrice_of_items_amt_vat_excl() {
        return price_of_items_amt_vat_excl;
    }
    public void setPrice_of_items_amt_vat_excl(String price_of_items_amt_vat_excl) {
        this.price_of_items_amt_vat_excl = price_of_items_amt_vat_excl;
    }
    public String getPromo_price_of_items_amt_vat_excl() {
        return promo_price_of_items_amt_vat_excl;
    }
    public void setPromo_price_of_items_amt_vat_excl(String promo_price_of_items_amt_vat_excl) {
        this.promo_price_of_items_amt_vat_excl = promo_price_of_items_amt_vat_excl;
    }
    public String getTotal_price_of_items_amt_vat_excl() {
        return total_price_of_items_amt_vat_excl;
    }
    public void setTotal_price_of_items_amt_vat_excl(String total_price_of_items_amt_vat_excl) {
        this.total_price_of_items_amt_vat_excl = total_price_of_items_amt_vat_excl;
    }
    public String getShip_charge_amt_vat_excl() {
        return ship_charge_amt_vat_excl;
    }
    public void setShip_charge_amt_vat_excl(String ship_charge_amt_vat_excl) {
        this.ship_charge_amt_vat_excl = ship_charge_amt_vat_excl;
    }
    public String getPromo_ship_charge_amt_vat_excl() {
        return promo_ship_charge_amt_vat_excl;
    }
    public void setPromo_ship_charge_amt_vat_excl(String promo_ship_charge_amt_vat_excl) {
        this.promo_ship_charge_amt_vat_excl = promo_ship_charge_amt_vat_excl;
    }
    public String getTotal_ship_charge_amt_vat_excl() {
        return total_ship_charge_amt_vat_excl;
    }
    public void setTotal_ship_charge_amt_vat_excl(String total_ship_charge_amt_vat_excl) {
        this.total_ship_charge_amt_vat_excl = total_ship_charge_amt_vat_excl;
    }
    public String getGift_wrap_amt_vat_excl() {
        return gift_wrap_amt_vat_excl;
    }
    public void setGift_wrap_amt_vat_excl(String gift_wrap_amt_vat_excl) {
        this.gift_wrap_amt_vat_excl = gift_wrap_amt_vat_excl;
    }
    public String getPromo_gift_wrap_amt_vat_excl() {
        return promo_gift_wrap_amt_vat_excl;
    }
    public void setPromo_gift_wrap_amt_vat_excl(String promo_gift_wrap_amt_vat_excl) {
        this.promo_gift_wrap_amt_vat_excl = promo_gift_wrap_amt_vat_excl;
    }
    public String getTotal_gift_wrap_amt_vat_excl() {
        return total_gift_wrap_amt_vat_excl;
    }
    public void setTotal_gift_wrap_amt_vat_excl(String total_gift_wrap_amt_vat_excl) {
        this.total_gift_wrap_amt_vat_excl = total_gift_wrap_amt_vat_excl;
    }
    public String getTotal_activity_value_amt_vat_excl() {
        return total_activity_value_amt_vat_excl;
    }
    public void setTotal_activity_value_amt_vat_excl(String total_activity_value_amt_vat_excl) {
        this.total_activity_value_amt_vat_excl = total_activity_value_amt_vat_excl;
    }
    public String getPrice_of_items_vat_rate_percent() {
        return price_of_items_vat_rate_percent;
    }
    public void setPrice_of_items_vat_rate_percent(String price_of_items_vat_rate_percent) {
        this.price_of_items_vat_rate_percent = price_of_items_vat_rate_percent;
    }
    public String getPrice_of_items_vat_amt() {
        return price_of_items_vat_amt;
    }
    public void setPrice_of_items_vat_amt(String price_of_items_vat_amt) {
        this.price_of_items_vat_amt = price_of_items_vat_amt;
    }
    public String getPromo_price_of_items_vat_amt() {
        return promo_price_of_items_vat_amt;
    }
    public void setPromo_price_of_items_vat_amt(String promo_price_of_items_vat_amt) {
        this.promo_price_of_items_vat_amt = promo_price_of_items_vat_amt;
    }
    public String getTotal_price_of_items_vat_amt() {
        return total_price_of_items_vat_amt;
    }
    public void setTotal_price_of_items_vat_amt(String total_price_of_items_vat_amt) {
        this.total_price_of_items_vat_amt = total_price_of_items_vat_amt;
    }
    public String getShip_charge_vat_rate_percent() {
        return ship_charge_vat_rate_percent;
    }
    public void setShip_charge_vat_rate_percent(String ship_charge_vat_rate_percent) {
        this.ship_charge_vat_rate_percent = ship_charge_vat_rate_percent;
    }
    public String getShip_charge_vat_amt() {
        return ship_charge_vat_amt;
    }
    public void setShip_charge_vat_amt(String ship_charge_vat_amt) {
        this.ship_charge_vat_amt = ship_charge_vat_amt;
    }
    public String getPromo_ship_charge_vat_amt() {
        return promo_ship_charge_vat_amt;
    }
    public void setPromo_ship_charge_vat_amt(String promo_ship_charge_vat_amt) {
        this.promo_ship_charge_vat_amt = promo_ship_charge_vat_amt;
    }
    public String getTotal_ship_charge_vat_amt() {
        return total_ship_charge_vat_amt;
    }
    public void setTotal_ship_charge_vat_amt(String total_ship_charge_vat_amt) {
        this.total_ship_charge_vat_amt = total_ship_charge_vat_amt;
    }
    public String getGift_wrap_vat_rate_percent() {
        return gift_wrap_vat_rate_percent;
    }
    public void setGift_wrap_vat_rate_percent(String gift_wrap_vat_rate_percent) {
        this.gift_wrap_vat_rate_percent = gift_wrap_vat_rate_percent;
    }
    public String getGift_wrap_vat_amt() {
        return gift_wrap_vat_amt;
    }
    public void setGift_wrap_vat_amt(String gift_wrap_vat_amt) {
        this.gift_wrap_vat_amt = gift_wrap_vat_amt;
    }
    public String getPromo_gift_wrap_vat_amt() {
        return promo_gift_wrap_vat_amt;
    }
    public void setPromo_gift_wrap_vat_amt(String promo_gift_wrap_vat_amt) {
        this.promo_gift_wrap_vat_amt = promo_gift_wrap_vat_amt;
    }
    public String getTotal_gift_wrap_vat_amt() {
        return total_gift_wrap_vat_amt;
    }
    public void setTotal_gift_wrap_vat_amt(String total_gift_wrap_vat_amt) {
        this.total_gift_wrap_vat_amt = total_gift_wrap_vat_amt;
    }
    public String getTotal_activity_value_vat_amt() {
        return total_activity_value_vat_amt;
    }
    public void setTotal_activity_value_vat_amt(String total_activity_value_vat_amt) {
        this.total_activity_value_vat_amt = total_activity_value_vat_amt;
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
    public String getGift_wrap_amt_vat_incl() {
        return gift_wrap_amt_vat_incl;
    }
    public void setGift_wrap_amt_vat_incl(String gift_wrap_amt_vat_incl) {
        this.gift_wrap_amt_vat_incl = gift_wrap_amt_vat_incl;
    }
    public String getPromo_gift_wrap_amt_vat_incl() {
        return promo_gift_wrap_amt_vat_incl;
    }
    public void setPromo_gift_wrap_amt_vat_incl(String promo_gift_wrap_amt_vat_incl) {
        this.promo_gift_wrap_amt_vat_incl = promo_gift_wrap_amt_vat_incl;
    }
    public String getTotal_gift_wrap_amt_vat_incl() {
        return total_gift_wrap_amt_vat_incl;
    }
    public void setTotal_gift_wrap_amt_vat_incl(String total_gift_wrap_amt_vat_incl) {
        this.total_gift_wrap_amt_vat_incl = total_gift_wrap_amt_vat_incl;
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
    public String getCommodity_code() {
        return commodity_code;
    }
    public void setCommodity_code(String commodity_code) {
        this.commodity_code = commodity_code;
    }
    public String getStatistical_code_depart() {
        return statistical_code_depart;
    }
    public void setStatistical_code_depart(String statistical_code_depart) {
        this.statistical_code_depart = statistical_code_depart;
    }
    public String getStatistical_code_arrival() {
        return statistical_code_arrival;
    }
    public void setStatistical_code_arrival(String statistical_code_arrival) {
        this.statistical_code_arrival = statistical_code_arrival;
    }
    public String getCommodity_code_supplementary_unit() {
        return commodity_code_supplementary_unit;
    }
    public void setCommodity_code_supplementary_unit(String commodity_code_supplementary_unit) {
        this.commodity_code_supplementary_unit = commodity_code_supplementary_unit;
    }
    public String getItem_qty_supplementary_unit() {
        return item_qty_supplementary_unit;
    }
    public void setItem_qty_supplementary_unit(String item_qty_supplementary_unit) {
        this.item_qty_supplementary_unit = item_qty_supplementary_unit;
    }
    public String getTotal_activity_supplementary_unit() {
        return total_activity_supplementary_unit;
    }
    public void setTotal_activity_supplementary_unit(String total_activity_supplementary_unit) {
        this.total_activity_supplementary_unit = total_activity_supplementary_unit;
    }
    public String getProduct_tax_code() {
        return product_tax_code;
    }
    public void setProduct_tax_code(String product_tax_code) {
        this.product_tax_code = product_tax_code;
    }
    public String getDepature_city() {
        return depature_city;
    }
    public void setDepature_city(String depature_city) {
        this.depature_city = depature_city;
    }
    public String getDeparture_country() {
        return departure_country;
    }
    public void setDeparture_country(String departure_country) {
        this.departure_country = departure_country;
    }
    public String getDeparture_post_code() {
        return departure_post_code;
    }
    public void setDeparture_post_code(String departure_post_code) {
        this.departure_post_code = departure_post_code;
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
    public String getSale_depart_country() {
        return sale_depart_country;
    }
    public void setSale_depart_country(String sale_depart_country) {
        this.sale_depart_country = sale_depart_country;
    }
    public String getSale_arrival_country() {
        return sale_arrival_country;
    }
    public void setSale_arrival_country(String sale_arrival_country) {
        this.sale_arrival_country = sale_arrival_country;
    }
    public String getTransportation_mode() {
        return transportation_mode;
    }
    public void setTransportation_mode(String transportation_mode) {
        this.transportation_mode = transportation_mode;
    }
    public String getDelivery_conditions() {
        return delivery_conditions;
    }
    public void setDelivery_conditions(String delivery_conditions) {
        this.delivery_conditions = delivery_conditions;
    }
    public String getSeller_depart_vat_number_country() {
        return seller_depart_vat_number_country;
    }
    public void setSeller_depart_vat_number_country(String seller_depart_vat_number_country) {
        this.seller_depart_vat_number_country = seller_depart_vat_number_country;
    }
    public String getSeller_depart_country_vat_number() {
        return seller_depart_country_vat_number;
    }
    public void setSeller_depart_country_vat_number(String seller_depart_country_vat_number) {
        this.seller_depart_country_vat_number = seller_depart_country_vat_number;
    }
    public String getSeller_arrival_vat_number_country() {
        return seller_arrival_vat_number_country;
    }
    public void setSeller_arrival_vat_number_country(String seller_arrival_vat_number_country) {
        this.seller_arrival_vat_number_country = seller_arrival_vat_number_country;
    }
    public String getSeller_arrival_country_vat_number() {
        return seller_arrival_country_vat_number;
    }
    public void setSeller_arrival_country_vat_number(String seller_arrival_country_vat_number) {
        this.seller_arrival_country_vat_number = seller_arrival_country_vat_number;
    }
    public String getTransaction_seller_vat_number_country() {
        return transaction_seller_vat_number_country;
    }
    public void setTransaction_seller_vat_number_country(String transaction_seller_vat_number_country) {
        this.transaction_seller_vat_number_country = transaction_seller_vat_number_country;
    }
    public String getTransaction_seller_vat_number() {
        return transaction_seller_vat_number;
    }
    public void setTransaction_seller_vat_number(String transaction_seller_vat_number) {
        this.transaction_seller_vat_number = transaction_seller_vat_number;
    }
    public String getBuyer_vat_number_country() {
        return buyer_vat_number_country;
    }
    public void setBuyer_vat_number_country(String buyer_vat_number_country) {
        this.buyer_vat_number_country = buyer_vat_number_country;
    }
    public String getBuyer_vat_number() {
        return buyer_vat_number;
    }
    public void setBuyer_vat_number(String buyer_vat_number) {
        this.buyer_vat_number = buyer_vat_number;
    }
    public String getVat_calculation_imputation_country() {
        return vat_calculation_imputation_country;
    }
    public void setVat_calculation_imputation_country(String vat_calculation_imputation_country) {
        this.vat_calculation_imputation_country = vat_calculation_imputation_country;
    }
    public String getTaxable_jurisdiction() {
        return taxable_jurisdiction;
    }
    public void setTaxable_jurisdiction(String taxable_jurisdiction) {
        this.taxable_jurisdiction = taxable_jurisdiction;
    }
    public String getTaxable_jurisdiction_level() {
        return taxable_jurisdiction_level;
    }
    public void setTaxable_jurisdiction_level(String taxable_jurisdiction_level) {
        this.taxable_jurisdiction_level = taxable_jurisdiction_level;
    }
    public String getVat_inv_number() {
        return vat_inv_number;
    }
    public void setVat_inv_number(String vat_inv_number) {
        this.vat_inv_number = vat_inv_number;
    }
    public String getVat_inv_converted_amt() {
        return vat_inv_converted_amt;
    }
    public void setVat_inv_converted_amt(String vat_inv_converted_amt) {
        this.vat_inv_converted_amt = vat_inv_converted_amt;
    }
    public String getVat_inv_currency_code() {
        return vat_inv_currency_code;
    }
    public void setVat_inv_currency_code(String vat_inv_currency_code) {
        this.vat_inv_currency_code = vat_inv_currency_code;
    }
    public String getVat_inv_exchange_rate() {
        return vat_inv_exchange_rate;
    }
    public void setVat_inv_exchange_rate(String vat_inv_exchange_rate) {
        this.vat_inv_exchange_rate = vat_inv_exchange_rate;
    }
    public String getVat_inv_exchange_rate_date() {
        return vat_inv_exchange_rate_date;
    }
    public void setVat_inv_exchange_rate_date(String vat_inv_exchange_rate_date) {
        this.vat_inv_exchange_rate_date = vat_inv_exchange_rate_date;
    }
    public String getExport_outside_eu() {
        return export_outside_eu;
    }
    public void setExport_outside_eu(String export_outside_eu) {
        this.export_outside_eu = export_outside_eu;
    }
    public String getInvoice_url() {
        return invoice_url;
    }
    public void setInvoice_url(String invoice_url) {
        this.invoice_url = invoice_url;
    }
    public String getBuyer_name() {
        return buyer_name;
    }
    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }
    public String getArrival_address() {
        return arrival_address;
    }
    public void setArrival_address(String arrival_address) {
        this.arrival_address = arrival_address;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getOrder_id() {
        return order_id;
    }
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    
    public String getSheet_name() {
        return sheet_name;
    }
    public void setSheet_name(String sheet_name) {
        this.sheet_name = sheet_name;
    }

}
