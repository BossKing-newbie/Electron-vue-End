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
    @GetMapping("/select")
    public List<Map> selectOrderForm(){
        return orderFormService.selectOrderForm();
    }
    @GetMapping("/getProducts/{productsId}")
    public Products getProducts(@PathVariable("productsId") String productsId){
        return orderFormService.getProducts(productsId);
    }
    @PostMapping("/insert")
    public Object placeOrder(PlaceOrderObject orderObject){
        return orderFormService.placeOrder(orderObject);
    }
}
