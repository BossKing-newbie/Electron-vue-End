package com.fosu.software.delivery.domain;

import java.io.Serializable;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.domain
 * @Author: Boss_king
 * @CreateTime: 2020-07-04 10:47
 * @Description: 产品列表类
 */
public class Products implements Serializable {
    private String productsId;
    private String productsName;
    private Double productsPrice;
    private Double productsWeight;
    private Double productsAddPrice;

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public Double getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(Double productsPrice) {
        this.productsPrice = productsPrice;
    }

    public Double getProductsWeight() {
        return productsWeight;
    }

    public void setProductsWeight(Double productsWeight) {
        this.productsWeight = productsWeight;
    }

    public Double getProductsAddPrice() {
        return productsAddPrice;
    }

    public void setProductsAddPrice(Double productsAddPrice) {
        this.productsAddPrice = productsAddPrice;
    }
}
