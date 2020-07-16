package com.fosu.software.delivery.dao;

import com.fosu.software.delivery.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.dao
 * @Author: Boss_king
 * @CreateTime: 2020-06-19 11:14
 * @Description: 订单类Mapper
 */
@Repository
public interface OrderFormMapper {
    // 返回orderForm订单
    public List<Map> selectOrderForm(String orderFormUserId);
    // 返回历史订单
    public List<Map> selectHistoryForm(String orderFormUserId);
    // 查询产品列表
    public Products getProducts(String productsId);
    // 插入OrderForm表
    public int insertOrderForm(OrderForm orderForm);
    // 插入OrderInfo表
    public int insertOrderInfo(OrderInfo orderInfo);
    // 插入ReserveForm表
    public int insertReserveForm(ReserveForm reserveForm);
    // 插入delivery_info表
    public int insertDeliveryInfo(DeliveryInfo deliveryInfo);
    // 更新delivery_info表
    public int updateDeliveryInfo(DeliveryInfo deliveryInfo);
    // 取消预约
    public int cancelReserve(String orderFormNumber);
    // 返回物流信息
    public DeliveryInfo deliveryInfo(String orderFormNumber);
    // 确认收货
    public int confirmDelivery(String orderFormNumber);
    // 返回预约中的订单
    public List<Map> selectReserveForm();
    // 更新方法，开单后重量填写update，价格update，状态变为已揽件
    public int updateOrderForm(Map<String,Object> map);
    // 返回物流状态查询信息
    public List<Map> orderCheck();
    // 返回已揽件的订单
    public List<String> alreadyPackage();
    // 返回已揽件订单的详细信息
    public List<Map> selectAlreadyPackage(String orderFormNumber);
    // 订单打回操作
    public Map<String,Object> goBackOrder(String orderFormNumber);
    // 订单通过操作
    public int insertWarehouse(Map<String,Object> map);
    // 返回入库信息
    public List<Map> selectWarehouse();
    // 立即出单下框数据回显
    public List<Map> selectedWarehouse();
    // 立即出单
    public int updateWarehouse(Map<String,Object> map);
    // 出库信息显示
    public List<Map> outputWarehouse();
}
