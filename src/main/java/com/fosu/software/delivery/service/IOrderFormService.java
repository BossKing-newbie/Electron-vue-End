package com.fosu.software.delivery.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    public List<Map> selectOrderForm(String orderFormUserId);
    // 返回历史订单
    public List<Map> selectHistoryForm(String orderFormUserId);
    // 查询产品列表
    public Products getProducts(String productsId);
    // 业务逻辑：用户填写下单后的操作
    public Object placeOrder(PlaceOrderObject orderObject) throws JsonProcessingException;
    // 取消预约，移除订单
    public Object cancelReserve(String orderFormNumber);
    // 返回物流信息
    public Object deliveryInfo(String orderFormNumber) throws Exception;
    // 确认收货
    public Object confirmDelivery(String orderFormNumber);
    // 返回预约中的订单
    public Object selectReserveForm();
    // 更新方法，开单后重量填写update，价格update，状态变为已揽件
    public Object updateOrderForm(Map<String,Object> map);
    // 返回物流信息状态查询
    public Object orderCheck();
    // 返回已揽件的订单同时生成流水单号
    public Object alreadyPackage();
    // 返回已揽件的订单信息
    public Object selectAlreadyPackage(String orderFormNumber);
    // 订单打回操作
    public Object goBackOrder(String orderFormNumber);
    // 订单通过写入仓库表，物流状态改为已入库，物流信息更新
    public Object insertWarehouse(Map<String,Object> map);
    // 返回入库信息
    public Object selectWarehouse();
    // 立即出单操作,业务逻辑：需要修改仓库表的出库人员，更新物流信息，更新物流状态为已发货
    public Object putOrder(Map<String,Object> map);
    // 立即出单下框数据回显
    public List<Map> selectedWarehouse();
    // 出库信息显示
    public Object outputWarehouse();
}
