package com.youwu.qucanguibuhuo.bean;

import androidx.databinding.BaseObservable;

import java.io.Serializable;

/**
 * 2021/11/24
 * 订单表
 * 金库
 */

public class OrderBean extends BaseObservable implements Serializable {
    private String order_id;//订单id
    private String order_number;//订单号
    private String order_start_time;//创建订单时间
    private String order_pay_time;//订单支付时间
    private String order_booking_time;//订单预约时间
    private String order_pickup_time;//取货时间
    private String order_phone;//订单联系电话
    private String order_user;//订单联系人
    private String order_name;//订单商品名称
    private String order_cabinet;//订单所在的柜子
    private String order_price;//订单应付价格
    private String order_real_pay_price;//订单实付价格
    private String order_pay_type;//支付方式
    private String order_coupons_name;//优惠券名称
    private String order_coupons_price;//优惠券价格
    private String order_state; //订单状态
    private String order_user_name; //购买人
    private String order_user_phone; //购买人手机号
    private String order_user_id; //购买人id


    private String order_bh_stock; //补货库存
    private String order_bh_yuyue; //补货预约

    private String order_bh_time; //补货时间
    private String order_bh_paragraph_num; //补货款数量
    private String order_bh_number; //补货数量
    private String order_bh_actual_number; //实际补货数量
    private String order_qh_number; //清货数量

    private String order_ch_order_number; //存货订单数量
    private String order_ch_order_time; //存货订单时间

    private String order_num;//预约套餐数量


    private int order_type;//1、预约 2、团购


    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getOrder_bh_stock() {
        return order_bh_stock;
    }

    public void setOrder_bh_stock(String order_bh_stock) {
        this.order_bh_stock = order_bh_stock;
    }

    public String getOrder_bh_yuyue() {
        return order_bh_yuyue;
    }

    public void setOrder_bh_yuyue(String order_bh_yuyue) {
        this.order_bh_yuyue = order_bh_yuyue;
    }

    public String getOrder_ch_order_time() {
        return order_ch_order_time;
    }

    public void setOrder_ch_order_time(String order_ch_order_time) {
        this.order_ch_order_time = order_ch_order_time;
    }

    public String getOrder_ch_order_number() {
        return order_ch_order_number;
    }

    public void setOrder_ch_order_number(String order_ch_order_number) {
        this.order_ch_order_number = order_ch_order_number;
    }

    public String getOrder_qh_number() {
        return order_qh_number;
    }

    public void setOrder_qh_number(String order_qh_number) {
        this.order_qh_number = order_qh_number;
    }

    public String getOrder_bh_actual_number() {
        return order_bh_actual_number;
    }

    public void setOrder_bh_actual_number(String order_bh_actual_number) {
        this.order_bh_actual_number = order_bh_actual_number;
    }

    public String getOrder_bh_number() {
        return order_bh_number;
    }

    public void setOrder_bh_number(String order_bh_number) {
        this.order_bh_number = order_bh_number;
    }

    public String getOrder_bh_paragraph_num() {
        return order_bh_paragraph_num;
    }

    public void setOrder_bh_paragraph_num(String order_bh_paragraph_num) {
        this.order_bh_paragraph_num = order_bh_paragraph_num;
    }

    public String getOrder_bh_time() {
        return order_bh_time;
    }

    public void setOrder_bh_time(String order_bh_time) {
        this.order_bh_time = order_bh_time;
    }

    public String getOrder_pay_time() {
        return order_pay_time;
    }

    public void setOrder_pay_time(String order_pay_time) {
        this.order_pay_time = order_pay_time;
    }

    public String getOrder_booking_time() {
        return order_booking_time;
    }

    public void setOrder_booking_time(String order_booking_time) {
        this.order_booking_time = order_booking_time;
    }

    public String getOrder_real_pay_price() {
        return order_real_pay_price;
    }

    public void setOrder_real_pay_price(String order_real_pay_price) {
        this.order_real_pay_price = order_real_pay_price;
    }

    public String getOrder_pay_type() {
        return order_pay_type;
    }

    public void setOrder_pay_type(String order_pay_type) {
        this.order_pay_type = order_pay_type;
    }

    public String getOrder_coupons_name() {
        return order_coupons_name;
    }

    public void setOrder_coupons_name(String order_coupons_name) {
        this.order_coupons_name = order_coupons_name;
    }

    public String getOrder_coupons_price() {
        return order_coupons_price;
    }

    public void setOrder_coupons_price(String order_coupons_price) {
        this.order_coupons_price = order_coupons_price;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public String getOrder_user_name() {
        return order_user_name;
    }

    public void setOrder_user_name(String order_user_name) {
        this.order_user_name = order_user_name;
    }

    public String getOrder_user_phone() {
        return order_user_phone;
    }

    public void setOrder_user_phone(String order_user_phone) {
        this.order_user_phone = order_user_phone;
    }

    public String getOrder_user_id() {
        return order_user_id;
    }

    public void setOrder_user_id(String order_user_id) {
        this.order_user_id = order_user_id;
    }

    public int getOrder_type() {
        return order_type;
    }

    public void setOrder_type(int order_type) {
        this.order_type = order_type;
    }

    public String getOrder_pickup_time() {
        return order_pickup_time;
    }

    public void setOrder_pickup_time(String order_pickup_time) {
        this.order_pickup_time = order_pickup_time;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getOrder_start_time() {
        return order_start_time;
    }

    public void setOrder_start_time(String order_start_time) {
        this.order_start_time = order_start_time;
    }

    public String getOrder_phone() {
        return order_phone;
    }

    public void setOrder_phone(String order_phone) {
        this.order_phone = order_phone;
    }

    public String getOrder_user() {
        return order_user;
    }

    public void setOrder_user(String order_user) {
        this.order_user = order_user;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOrder_cabinet() {
        return order_cabinet;
    }

    public void setOrder_cabinet(String order_cabinet) {
        this.order_cabinet = order_cabinet;
    }
}
