package com.fosu.software.delivery.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.domain
 * @Author: Boss_king
 * @CreateTime: 2020-06-19 10:51
 * @Description: 订单实体类
 */
public class OrderForm implements Serializable {
    /*数据库字段
    * order_form_number	varchar
    order_form_products_id	varchar
    order_form_user_id	varchar
    order_form_time	datetime
    order_form_start_address	varchar
    order_form_end_address	varchar
    order_form_weight	double
    order_form_money	double
    order_form_status	varchar
    order_form_way	varchar

*/
    // 定义时间转换格式
    private static final String DDFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_ZONE = "GMT+8";

    private String orderFormNumber;
    private String orderFormProductsId;
    private String orderFormUserId;
    @JsonFormat(pattern = DDFormat, timezone = TIME_ZONE)
    private Timestamp orderFormTime;
    private String orderFormStartAddress;
    private String orderFormEndAddress;
    private Double orderFormWeight;
    private Double orderFormMoney;
    private String orderFormStatus;
    private String orderFormWay;

    public static String getDDFormat() {
        return DDFormat;
    }

    public static String getTimeZone() {
        return TIME_ZONE;
    }

    public String getOrderFormNumber() {
        return orderFormNumber;
    }

    public void setOrderFormNumber(String orderFormNumber) {
        this.orderFormNumber = orderFormNumber;
    }

    public String getOrderFormProductsId() {
        return orderFormProductsId;
    }

    public void setOrderFormProductsId(String orderFormProductId) {
        this.orderFormProductsId = orderFormProductId;
    }

    public String getOrderFormUserId() {
        return orderFormUserId;
    }

    public void setOrderFormUserId(String orderFormUserId) {
        this.orderFormUserId = orderFormUserId;
    }

    public Timestamp getOrderFormTime() {
        return orderFormTime;
    }

    public void setOrderFormTime(Timestamp orderFormTime) {
        this.orderFormTime = orderFormTime;
    }

    public String getOrderFormStartAddress() {
        return orderFormStartAddress;
    }

    public void setOrderFormStartAddress(String orderFormStartAddress) {
        this.orderFormStartAddress = orderFormStartAddress;
    }

    public String getOrderFormEndAddress() {
        return orderFormEndAddress;
    }

    public void setOrderFormEndAddress(String orderFormEndAddress) {
        this.orderFormEndAddress = orderFormEndAddress;
    }

    public Double getOrderFormWeight() {
        return orderFormWeight;
    }

    public void setOrderFormWeight(Double orderFormWeight) {
        this.orderFormWeight = orderFormWeight;
    }

    public Double getOrderFormMoney() {
        return orderFormMoney;
    }

    public void setOrderFormMoney(Double orderFormMoney) {
        this.orderFormMoney = orderFormMoney;
    }

    public String getOrderFormStatus() {
        return orderFormStatus;
    }

    public void setOrderFormStatus(String orderFormStatus) {
        this.orderFormStatus = orderFormStatus;
    }

    public String getOrderFormWay() {
        return orderFormWay;
    }

    public void setOrderFormWay(String orderFormWay) {
        this.orderFormWay = orderFormWay;
    }

    @Override
    public String toString() {
        return "OrderForm{" +
                "orderFormNumber='" + orderFormNumber + '\'' +
                ", orderFormProductId='" + orderFormProductsId + '\'' +
                ", orderFormUserId='" + orderFormUserId + '\'' +
                ", orderFormTime=" + orderFormTime +
                ", orderFormStartAddress='" + orderFormStartAddress + '\'' +
                ", orderFormEndAddress='" + orderFormEndAddress + '\'' +
                ", orderFormWeight=" + orderFormWeight +
                ", orderFormMoney=" + orderFormMoney +
                ", orderFormStatus='" + orderFormStatus + '\'' +
                ", orderFormWay='" + orderFormWay + '\'' +
                '}';
    }
}
