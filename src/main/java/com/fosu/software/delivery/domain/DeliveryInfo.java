package com.fosu.software.delivery.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.domain
 * @Author: Boss_king
 * @CreateTime: 2020-07-07 22:15
 * @Description: delivery_info表的实体类
 */
public class DeliveryInfo implements Serializable {
    private String orderFormNumber;
    private String orderDelivery;

    public String getOrderFormNumber() {
        return orderFormNumber;
    }

    public void setOrderFormNumber(String orderFormNumber) {
        this.orderFormNumber = orderFormNumber;
    }

    public String getOrderDelivery() {
        return orderDelivery;
    }

    public void setOrderDelivery(String orderDelivery) {
        this.orderDelivery = orderDelivery;
    }

    @Override
    public String toString() {
        return "DeliveryInfo{" +
                "orderFormNumber='" + orderFormNumber + '\'' +
                ", orderDelivery='" + orderDelivery + '\'' +
                '}';
    }
}
