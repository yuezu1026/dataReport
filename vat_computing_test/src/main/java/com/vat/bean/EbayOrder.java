package com.vat.bean;

import java.io.Serializable;

public class EbayOrder implements Serializable {

    private static final long serialVersionUID = -3398999423865022901L;
    
    private String ebay_order_id;
    private String ebay_user_id;
    
    private String sheet_name;
    private String sales_record_number;
    private String user_id;
    private String buyer_fullname;
    private String buyer_phone_number;
    private String buyer_email;
    private String buyer_address_1;
    private String buyer_address_2;
    private String buyer_city;
    private String buyer_state;
    private String buyer_zip;
    private String buyer_country;
    private String order_id;
    private String item_id;
    private String transaction_id;
    private String item_title;
    private String quantity;
    private String sale_price;
    private String shipping_and_handling;
    private String sales_tax;
    private String insurance;
    private String total_price;
    private String payment_method;
    private String paypal_transaction_id;
    private String sale_date;
    private String checkout_date;
    private String paid_on_date;
    private String shipped_on_date;
    private String shipping_service;
    public String getSheet_name() {
        return sheet_name;
    }
    public void setSheet_name(String sheet_name) {
        this.sheet_name = sheet_name;
    }
    private String feedback_left;
    private String feedback_received;
    private String notes_to_yourself;
    private String custom_label;
    private String listed_on;
    private String sold_on;
    private String private_notes;
    private String product_id_type;
    private String product_id_value;
    private String product_id_value_2;
    private String variation_details;
    private String product_reference_id;
    private String tracking_number;
    private String phone;
    
    public String getSales_record_number() {
        return sales_record_number;
    }
    public void setSales_record_number(String sales_record_number) {
        this.sales_record_number = sales_record_number;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getBuyer_fullname() {
        return buyer_fullname;
    }
    public void setBuyer_fullname(String buyer_fullname) {
        this.buyer_fullname = buyer_fullname;
    }
    public String getBuyer_phone_number() {
        return buyer_phone_number;
    }
    public void setBuyer_phone_number(String buyer_phone_number) {
        this.buyer_phone_number = buyer_phone_number;
    }
    public String getBuyer_email() {
        return buyer_email;
    }
    public void setBuyer_email(String buyer_email) {
        this.buyer_email = buyer_email;
    }
    public String getBuyer_address_1() {
        return buyer_address_1;
    }
    public void setBuyer_address_1(String buyer_address_1) {
        this.buyer_address_1 = buyer_address_1;
    }
    public String getBuyer_address_2() {
        return buyer_address_2;
    }
    public void setBuyer_address_2(String buyer_address_2) {
        this.buyer_address_2 = buyer_address_2;
    }
    public String getBuyer_city() {
        return buyer_city;
    }
    public void setBuyer_city(String buyer_city) {
        this.buyer_city = buyer_city;
    }
    public String getBuyer_state() {
        return buyer_state;
    }
    public void setBuyer_state(String buyer_state) {
        this.buyer_state = buyer_state;
    }
    public String getBuyer_zip() {
        return buyer_zip;
    }
    public void setBuyer_zip(String buyer_zip) {
        this.buyer_zip = buyer_zip;
    }
    public String getBuyer_country() {
        return buyer_country;
    }
    public void setBuyer_country(String buyer_country) {
        this.buyer_country = buyer_country;
    }
    public String getOrder_id() {
        return order_id;
    }
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    public String getItem_id() {
        return item_id;
    }
    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }
    public String getTransaction_id() {
        return transaction_id;
    }
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    public String getItem_title() {
        return item_title;
    }
    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getSale_price() {
        return sale_price;
    }
    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }
    public String getShipping_and_handling() {
        return shipping_and_handling;
    }
    public void setShipping_and_handling(String shipping_and_handling) {
        this.shipping_and_handling = shipping_and_handling;
    }
    public String getSales_tax() {
        return sales_tax;
    }
    public void setSales_tax(String sales_tax) {
        this.sales_tax = sales_tax;
    }
    public String getInsurance() {
        return insurance;
    }
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
  
    public String getPayment_method() {
        return payment_method;
    }
    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
    public String getPaypal_transaction_id() {
        return paypal_transaction_id;
    }
    public void setPaypal_transaction_id(String paypal_transaction_id) {
        this.paypal_transaction_id = paypal_transaction_id;
    }
    public String getSale_date() {
        return sale_date;
    }
    public void setSale_date(String sale_date) {
        this.sale_date = sale_date;
    }
    public String getCheckout_date() {
        return checkout_date;
    }
    public void setCheckout_date(String checkout_date) {
        this.checkout_date = checkout_date;
    }
    public String getPaid_on_date() {
        return paid_on_date;
    }
    public void setPaid_on_date(String paid_on_date) {
        this.paid_on_date = paid_on_date;
    }
    public String getShipped_on_date() {
        return shipped_on_date;
    }
    public void setShipped_on_date(String shipped_on_date) {
        this.shipped_on_date = shipped_on_date;
    }
    public String getShipping_service() {
        return shipping_service;
    }
    public void setShipping_service(String shipping_service) {
        this.shipping_service = shipping_service;
    }
    public String getFeedback_left() {
        return feedback_left;
    }
    public void setFeedback_left(String feedback_left) {
        this.feedback_left = feedback_left;
    }
    public String getFeedback_received() {
        return feedback_received;
    }
    public void setFeedback_received(String feedback_received) {
        this.feedback_received = feedback_received;
    }
    public String getNotes_to_yourself() {
        return notes_to_yourself;
    }
    public void setNotes_to_yourself(String notes_to_yourself) {
        this.notes_to_yourself = notes_to_yourself;
    }
    public String getCustom_label() {
        return custom_label;
    }
    public void setCustom_label(String custom_label) {
        this.custom_label = custom_label;
    }
    public String getListed_on() {
        return listed_on;
    }
    public void setListed_on(String listed_on) {
        this.listed_on = listed_on;
    }
    public String getSold_on() {
        return sold_on;
    }
    public void setSold_on(String sold_on) {
        this.sold_on = sold_on;
    }
    public String getPrivate_notes() {
        return private_notes;
    }
    public void setPrivate_notes(String private_notes) {
        this.private_notes = private_notes;
    }
    public String getProduct_id_type() {
        return product_id_type;
    }
    public void setProduct_id_type(String product_id_type) {
        this.product_id_type = product_id_type;
    }
    public String getProduct_id_value() {
        return product_id_value;
    }
    public void setProduct_id_value(String product_id_value) {
        this.product_id_value = product_id_value;
    }
    public String getProduct_id_value_2() {
        return product_id_value_2;
    }
    public void setProduct_id_value_2(String product_id_value_2) {
        this.product_id_value_2 = product_id_value_2;
    }
    public String getVariation_details() {
        return variation_details;
    }
    public void setVariation_details(String variation_details) {
        this.variation_details = variation_details;
    }
    public String getProduct_reference_id() {
        return product_reference_id;
    }
    public void setProduct_reference_id(String product_reference_id) {
        this.product_reference_id = product_reference_id;
    }
    public String getTracking_number() {
        return tracking_number;
    }
    public void setTracking_number(String tracking_number) {
        this.tracking_number = tracking_number;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEbay_order_id() {
        return ebay_order_id;
    }
    public void setEbay_order_id(String ebay_order_id) {
        this.ebay_order_id = ebay_order_id;
    }
    public String getEbay_user_id() {
        return ebay_user_id;
    }
    public void setEbay_user_id(String ebay_user_id) {
        this.ebay_user_id = ebay_user_id;
    }
    public String getTotal_price() {
        return total_price;
    }
    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
    

}
