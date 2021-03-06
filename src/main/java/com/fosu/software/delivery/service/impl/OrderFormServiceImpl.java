package com.fosu.software.delivery.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fosu.software.delivery.dao.OrderFormMapper;
import com.fosu.software.delivery.domain.*;
import com.fosu.software.delivery.resultFormat.ResultUtils;
import com.fosu.software.delivery.service.IOrderFormService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.service.impl
 * @Author: Boss_king
 * @CreateTime: 2020-06-19 12:33
 * @Description: 订单业务层接口实现类
 */
@Service("orderFormService")
public class OrderFormServiceImpl implements IOrderFormService {
    private OrderFormMapper orderFormMapper;

    @Autowired
    public void setOrderFormMapper(OrderFormMapper orderFormMapper) {
        this.orderFormMapper = orderFormMapper;
    }

    @Override
    public List<Map> selectOrderForm(String orderFormUserId) {
        List<Map> mapList = new ArrayList<>();
        List<Map> orderFormList = orderFormMapper.selectOrderForm(orderFormUserId);
        // 进行前端格式转换，转化为前端要求返回的字段名
        for (Map orderForm:orderFormList) {
            Map<String,Object> map = new HashMap<>();
            map.put("number",orderForm.get("orderFormNumber"));
            map.put("id",orderForm.get("productsName"));
            map.put("date",orderForm.get("orderFormTime"));
            map.put("ship_address",orderForm.get("orderFormStartAddress"));
            map.put("address",orderForm.get("orderFormEndAddress"));
            map.put("price",orderForm.get("orderFormMoney"));
            map.put("payment",orderForm.get("orderFormWay"));
            map.put("status",orderForm.get("orderFormStatus"));
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public List<Map> selectHistoryForm(String orderFormUserId) {
        List<Map> historyList = new ArrayList<>();
        List<Map> orderFormList = orderFormMapper.selectHistoryForm(orderFormUserId);
        // 进行前端格式转换，转化为前端要求返回的字段名
        for (Map orderForm:orderFormList) {
            Map<String,Object> map = new HashMap<>();
            map.put("number",orderForm.get("orderFormNumber"));
            map.put("id",orderForm.get("productsName"));
            map.put("date",orderForm.get("orderFormTime"));
            map.put("ship_address",orderForm.get("orderFormStartAddress"));
            map.put("address",orderForm.get("orderFormEndAddress"));
            map.put("price",orderForm.get("orderFormMoney"));
            map.put("payment",orderForm.get("orderFormWay"));
            map.put("status",orderForm.get("orderFormStatus"));
            historyList.add(map);
        }
        return historyList;
    }

    @Override
    public Products getProducts(String productsId) {
        return orderFormMapper.getProducts(productsId);
    }

    @Override
    // 声明事务回滚，如果发生异常则不进行插入
    @Transactional
    public Object placeOrder(PlaceOrderObject orderObject) throws JsonProcessingException {
        // 创建四个表的联结对象
        OrderForm orderForm = new OrderForm();
        OrderInfo orderInfo = new OrderInfo();
        ReserveForm reserveForm = new ReserveForm();
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        // 产生8位随机订单号,生成指定长度的字母和数字的随机组合字符串
        String orderId = RandomStringUtils.randomAlphanumeric(8);
        // orderForm表
        orderForm.setOrderFormNumber(orderId);
        orderForm.setOrderFormProductsId(orderObject.getOrderFormProductsId());
        orderForm.setOrderFormUserId(orderObject.getOrderFormUserId());
        orderForm.setOrderFormStartAddress(orderObject.getOrderFormStartAddress());
        orderForm.setOrderFormEndAddress(orderObject.getOrderFormEndAddress());
        // 获取当前时间
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        orderForm.setOrderFormTime(timestamp);
        orderForm.setOrderFormMoney(orderObject.getOrderFormMoney());
        orderForm.setOrderFormWeight(0.0);
        orderForm.setOrderFormStatus("预约中");
        orderForm.setOrderFormWay("未付款");
        // orderInfo表
        orderInfo.setOrderFormNumber(orderId);
        orderInfo.setOrderSender(orderObject.getOrderSender());
        orderInfo.setOrderSenderPhone(orderObject.getOrderSenderPhone());
        orderInfo.setOrderRecipient(orderObject.getOrderRecipient());
        orderInfo.setOrderRecipientPhone(orderObject.getOrderRecipientPhone());
        // reserveForm表
        reserveForm.setReserveId(orderId);
        reserveForm.setReserveTime(orderObject.getReserveTime());
        // delivery_info表
        deliveryInfo.setOrderFormNumber(orderId);
        List<DeliveryTimestamp> deliveryTimestampList = new ArrayList<>();
        DeliveryTimestamp deliveryTimestamp = new DeliveryTimestamp();
        deliveryTimestamp.setContent("成功预约");
        deliveryTimestamp.setTimestamp(timestamp);
        deliveryTimestampList.add(deliveryTimestamp);
        deliveryInfo.setOrderDelivery(new ObjectMapper().writeValueAsString(deliveryTimestampList));
        // 创建插入语句的标识，如果insert成功则返回1，失败返回0
        int code = 0;
        Map<String,String> map = new HashMap<>();
        try{
            code += orderFormMapper.insertOrderForm(orderForm);
            code += orderFormMapper.insertOrderInfo(orderInfo);
            code += orderFormMapper.insertReserveForm(reserveForm);
            code += orderFormMapper.insertDeliveryInfo(deliveryInfo);
            if(code == 4) {
                map.put("msg","插入成功");
                return ResultUtils.success(map);
            }else {
                return ResultUtils.fail(400,"部分字段插入失败!");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }

    @Override
    @Transactional
    public Object cancelReserve(String orderFormNumber) {
        try {
            int code = orderFormMapper.cancelReserve(orderFormNumber);
            if (code == 1) {
                return ResultUtils.success();
            } else {
                return ResultUtils.fail(400,"取消失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }

    @Override
    public Object deliveryInfo(String orderFormNumber){
        try{
            DeliveryInfo info = orderFormMapper.deliveryInfo(orderFormNumber);
            // 反序列对象数组
            List<DeliveryTimestamp> deliveryTimestampList = new ObjectMapper().readValue(info.getOrderDelivery(),
                    new TypeReference<List<DeliveryTimestamp>>(){});
            return ResultUtils.success(deliveryTimestampList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }

    @Override
    public Object confirmDelivery(String orderFormNumber) {
        try {
            int code = orderFormMapper.confirmDelivery(orderFormNumber);
            if (code >0){
                return ResultUtils.success();
            } else {
                return ResultUtils.fail(400,"确认失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }

    @Override
    public Object selectReserveForm() {
        try {
            List<Map> mapList = orderFormMapper.selectReserveForm();
            for (Map map : mapList) {
                map.put("sum",map.get("price"));
                map.put("weight",0);
            }
            return ResultUtils.success(mapList);
        }catch (Exception e){
            return ResultUtils.fail(500,e.getLocalizedMessage());
        }

    }

    @Override
    @Transactional
    public Object updateOrderForm(Map<String, Object> map) {
        try{
            int code = orderFormMapper.updateOrderForm(map);
            // delivery_info表
            String orderFormNumber = (String) map.get("num");
            DeliveryInfo deliveryInfo = orderFormMapper.deliveryInfo(orderFormNumber);
            // 反序列对象数组
            List<DeliveryTimestamp> deliveryTimestampList = new ObjectMapper().readValue(deliveryInfo.getOrderDelivery(),
                    new TypeReference<List<DeliveryTimestamp>>(){});
            // 获取当前时间
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            DeliveryTimestamp deliveryTimestamp = new DeliveryTimestamp();
            deliveryTimestamp.setContent("已揽件");
            deliveryTimestamp.setTimestamp(timestamp);
            deliveryTimestampList.add(deliveryTimestamp);
            deliveryInfo.setOrderDelivery(new ObjectMapper().writeValueAsString(deliveryTimestampList));
            // 更新deliveryInfo表,即更新物流信息
            code += orderFormMapper.updateDeliveryInfo(deliveryInfo);
            if (code > 0) {
                return ResultUtils.success();
            }else {
                return ResultUtils.fail(400,"更新失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }

    @Override
    public Object orderCheck() {
        try{
            List<Map> map = orderFormMapper.orderCheck();
            return ResultUtils.success(map);
        }catch (Exception e){
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }

    @Override
    public Object alreadyPackage() {
        Map<String,Object> map = new HashMap<>();
        try {
            List<String> list = orderFormMapper.alreadyPackage();
            map.put("wareHouseNum",RandomStringUtils.randomAlphanumeric(10));
            map.put("list",list);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }

    @Override
    public Object selectAlreadyPackage(String orderFormNumber) {
        try{
            List<Map> mapList = orderFormMapper.selectAlreadyPackage(orderFormNumber);
            return ResultUtils.success(mapList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }

    @Override
    public Object goBackOrder(String orderFormNumber) {
        try{
            Map<String,Object> orderMap = orderFormMapper.goBackOrder(orderFormNumber);
            Double weight = (Double) orderMap.get("weight");
            Double money = (Double) orderMap.get("money");
            Double initWeight = (Double) orderMap.get("initWeight");
            Double addPrice = (Double) orderMap.get("addPrice");
            money = money - (weight-initWeight) * addPrice;
            Map<String,Object> map = new HashMap<>();
            map.put("weight",0.0);
            map.put("sum",money);
            map.put("status","预约中");
            map.put("num", orderFormNumber);
            int code = orderFormMapper.updateOrderForm(map);
            if(code > 0) {
                return ResultUtils.success();
            } else {
                return ResultUtils.fail(400,"插入失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }

    @Override
    public Object insertWarehouse(Map<String, Object> map) {
        try{
            Map<String,Object> orderMap = orderFormMapper.goBackOrder((String) map.get("orderFormNumber"));
            Map<String,Object> updateMap = new HashMap<>();
            // 铺垫后续map
            updateMap.put("weight",orderMap.get("weight"));
            updateMap.put("sum",orderMap.get("money"));
            updateMap.put("num",map.get("orderFormNumber"));
            updateMap.put("status","已入库");
            // 插入仓库表
            int code = orderFormMapper.insertWarehouse(map);
            // 更新物流状态
            code += orderFormMapper.updateOrderForm(updateMap);
            // 物流信息更新为已入库
            // delivery_info表
            String orderFormNumber = (String) map.get("orderFormNumber");
            DeliveryInfo deliveryInfo = orderFormMapper.deliveryInfo(orderFormNumber);
            // 反序列对象数组
            List<DeliveryTimestamp> deliveryTimestampList = new ObjectMapper().readValue(deliveryInfo.getOrderDelivery(),
                    new TypeReference<List<DeliveryTimestamp>>(){});
            // 获取当前时间
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            DeliveryTimestamp deliveryTimestamp = new DeliveryTimestamp();
            deliveryTimestamp.setContent("已入库");
            deliveryTimestamp.setTimestamp(timestamp);
            deliveryTimestampList.add(deliveryTimestamp);
            deliveryInfo.setOrderDelivery(new ObjectMapper().writeValueAsString(deliveryTimestampList));
            // 更新deliveryInfo表,即更新物流信息
            code += orderFormMapper.updateDeliveryInfo(deliveryInfo);
            if(code == 3){
                return ResultUtils.success();
            }else {
                return ResultUtils.fail(400,"入库失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }

    @Override
    public Object selectWarehouse() {
        try{
            return ResultUtils.success(orderFormMapper.selectWarehouse());
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }
    // 立即出单操作,业务逻辑：需要修改仓库表的出库人员，更新物流信息，更新物流状态为已发货
    @Override
    public Object putOrder(Map<String,Object> map) {
        try{
            // 修改仓库表的出库人员
            int code = orderFormMapper.updateWarehouse(map);
            Map<String,Object> orderMap = orderFormMapper.goBackOrder((String) map.get("orderFormNumber"));
            Map<String,Object> updateMap = new HashMap<>();
            // 铺垫后续map
            updateMap.put("weight",orderMap.get("weight"));
            updateMap.put("sum",orderMap.get("money"));
            updateMap.put("num",map.get("orderFormNumber"));
            updateMap.put("status","已发货");
            // 更新物流状态为已发货
            code += orderFormMapper.updateOrderForm(updateMap);
            // delivery_info表
            String orderFormNumber = (String) map.get("orderFormNumber");
            DeliveryInfo deliveryInfo = orderFormMapper.deliveryInfo(orderFormNumber);
            // 反序列对象数组
            List<DeliveryTimestamp> deliveryTimestampList = new ObjectMapper().readValue(deliveryInfo.getOrderDelivery(),
                    new TypeReference<List<DeliveryTimestamp>>(){});
            // 获取当前时间
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            DeliveryTimestamp deliveryTimestamp = new DeliveryTimestamp();
            deliveryTimestamp.setContent("已发货");
            deliveryTimestamp.setTimestamp(timestamp);
            deliveryTimestampList.add(deliveryTimestamp);
            deliveryInfo.setOrderDelivery(new ObjectMapper().writeValueAsString(deliveryTimestampList));
            // 更新deliveryInfo表,即更新物流信息
            code += orderFormMapper.updateDeliveryInfo(deliveryInfo);
            if(code == 3){
                return ResultUtils.success();
            }else {
                return ResultUtils.fail(400,"发货失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Map> selectedWarehouse() {
        return orderFormMapper.selectedWarehouse();
    }

    @Override
    public Object outputWarehouse() {
        try{
            return ResultUtils.success(orderFormMapper.outputWarehouse());
        }catch (Exception e){
            return ResultUtils.fail(400,"查询失败");
        }
    }
}
