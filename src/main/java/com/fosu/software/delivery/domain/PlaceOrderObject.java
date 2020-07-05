package com.fosu.software.delivery.domain;

import java.io.Serializable;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.domain
 * @Author: Boss_king
 * @CreateTime: 2020-07-05 09:32
 * @Description: 下单后前端传递的对象object
 */
public class PlaceOrderObject implements Serializable {
    /*orderSender: this.senderName,
        orderSenderPhone: this.senderNumber,
        orderRecipient: this.receiveName,
        orderRecipientPhone: this.receiveNumber,
        orderFormStartAddress: this.senderAddress,
        orderFormEndAddress: this.receiveAddress,
        orderFormUserId: data.userId,
        reserveTime: this.deliveryDetails[2].time,
        orderFormProductsId: this.productsId,
        orderFormMoney: this.money*/
    private String orderSender;
    private String orderSenderPhone;
    private String orderRecipient;
    private String orderRecipientPhone;
    private String orderFormStartAddress;
    private String orderFormEndAddress;
    private String orderFormUserId;
    private String reserveTime;
    private String orderFormProductsId;
    private Double orderFormMoney;

    public String getOrderSender() {
        return orderSender;
    }

    public void setOrderSender(String orderSender) {
        this.orderSender = orderSender;
    }

    public String getOrderSenderPhone() {
        return orderSenderPhone;
    }

    public void setOrderSenderPhone(String orderSenderPhone) {
        this.orderSenderPhone = orderSenderPhone;
    }

    public String getOrderRecipient() {
        return orderRecipient;
    }

    public void setOrderRecipient(String orderRecipient) {
        this.orderRecipient = orderRecipient;
    }

    public String getOrderRecipientPhone() {
        return orderRecipientPhone;
    }

    public void setOrderRecipientPhone(String orderRecipientPhone) {
        this.orderRecipientPhone = orderRecipientPhone;
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

    public String getOrderFormUserId() {
        return orderFormUserId;
    }

    public void setOrderFormUserId(String orderFormUserId) {
        this.orderFormUserId = orderFormUserId;
    }

    public String getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(String reserveTime) {
        this.reserveTime = reserveTime;
    }

    public String getOrderFormProductsId() {
        return orderFormProductsId;
    }

    public void setOrderFormProductsId(String orderFormProductsId) {
        this.orderFormProductsId = orderFormProductsId;
    }

    public Double getOrderFormMoney() {
        return orderFormMoney;
    }

    public void setOrderFormMoney(Double orderFormMoney) {
        this.orderFormMoney = orderFormMoney;
    }
}
