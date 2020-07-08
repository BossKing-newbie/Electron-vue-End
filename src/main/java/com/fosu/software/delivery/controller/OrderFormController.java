package com.fosu.software.delivery.controller;

import com.fosu.software.delivery.domain.PlaceOrderObject;
import com.fosu.software.delivery.domain.Products;
import com.fosu.software.delivery.service.impl.OrderFormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.controller
 * @Author: Boss_king
 * @CreateTime: 2020-06-19 12:35
 * @Description: 订单类控制层
 */
@RestController
@RequestMapping("/order")
public class OrderFormController {
    private OrderFormServiceImpl orderFormService;
    @Autowired
    public void setOrderFormService(OrderFormServiceImpl orderFormService) {
        this.orderFormService = orderFormService;
    }
    @GetMapping("/select/{userId}")
    public List<Map> selectOrderForm(@PathVariable("userId") String userId){
        return orderFormService.selectOrderForm(userId);
    }
    @GetMapping("/history/{userId}")
    public List<Map> selectHistoryForm(@PathVariable("userId") String userId){
        return orderFormService.selectHistoryForm(userId);
    }
    @GetMapping("/getProducts/{productsId}")
    public Products getProducts(@PathVariable("productsId") String productsId){
        return orderFormService.getProducts(productsId);
    }
    @PostMapping("/insert")
    public Object placeOrder(PlaceOrderObject orderObject) throws Exception{
        return orderFormService.placeOrder(orderObject);
    }
    @DeleteMapping("/cancel/{number}")
    public Object cancelReserve(@PathVariable("number") String orderFormNumber){
        return orderFormService.cancelReserve(orderFormNumber);
    }
    @GetMapping("/delivery/{orderFormNumber}")
    public Object deliveryInfo(@PathVariable("orderFormNumber") String orderFormNumber){
        return orderFormService.deliveryInfo(orderFormNumber);
    }
    @GetMapping("/confirm/{orderFormNumber}")
    public Object confirmDelivery(@PathVariable("orderFormNumber") String orderFormNumber){
        return orderFormService.confirmDelivery(orderFormNumber);
    }
}
