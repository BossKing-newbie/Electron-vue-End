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
    // 取消预约
    public int cancelReserve(String orderFormNumber);
    // 返回物流信息
    public DeliveryInfo deliveryInfo(String orderFormNumber);
    // 确认收货
    public int confirmDelivery(String orderFormNumber);
}
