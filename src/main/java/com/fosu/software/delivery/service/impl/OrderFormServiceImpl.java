package com.fosu.software.delivery.service.impl;

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
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Map> selectOrderForm() {
        return orderFormMapper.selectOrderForm();
    }

    @Override
    public Products getProducts(String productsId) {
        return orderFormMapper.getProducts(productsId);
    }

    @Override
    // 声明事务回滚，如果发生异常则不进行插入
    @Transactional
    public Object placeOrder(PlaceOrderObject orderObject) {
        // 创建三个表的联结对象
        OrderForm orderForm = new OrderForm();
        OrderInfo orderInfo = new OrderInfo();
        ReserveForm reserveForm = new ReserveForm();
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
        System.out.println(timestamp);
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
        // 创建插入语句的标识，如果insert成功则返回1，失败返回0
        int code = 0;
        Map<String,String> map = new HashMap<>();
        try{
            code += orderFormMapper.insertOrderForm(orderForm);
            code += orderFormMapper.insertOrderInfo(orderInfo);
            code += orderFormMapper.insertReserveForm(reserveForm);
            if(code == 3) {
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
}
