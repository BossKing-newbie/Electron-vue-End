package com.fosu.software.delivery.service;

import com.fosu.software.delivery.domain.PlaceOrderObject;
import com.fosu.software.delivery.domain.Products;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.service
 * @Author: Boss_king
 * @CreateTime: 2020-06-19 12:33
 * @Description: 订单业务层级接口
 */
public interface IOrderFormService {
    // 返回orderForm订单
    public List<Map> selectOrderForm();
    // 查询产品列表
    public Products getProducts(String productsId);
    // 业务逻辑：用户填写下单后的操作
    public Object placeOrder(PlaceOrderObject orderObject);
}
