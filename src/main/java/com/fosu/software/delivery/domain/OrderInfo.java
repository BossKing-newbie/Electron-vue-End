package com.fosu.software.delivery.domain;

import java.io.Serializable;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.domain
 * @Author: Boss_king
 * @CreateTime: 2020-07-04 23:15
 * @Description: 订单详细表
 */
public class OrderInfo implements Serializable {
    /*order_form_number
    order_sender
    order_sender_phone
    order_recipient
    order_recipient_phone
    */
    private String orderFormNumber;
    private String orderSender;
    private String orderSenderPhone;
    private String orderRecipient;
    private String orderRecipientPhone;

    public String getOrderFormNumber() {
        return orderFormNumber;
    }

    public void setOrderFormNumber(String orderFormNumber) {
        this.orderFormNumber = orderFormNumber;
    }

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
}
