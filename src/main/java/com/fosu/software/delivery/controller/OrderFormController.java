package com.fosu.software.delivery.controller;

import com.fosu.software.delivery.domain.PlaceOrderObject;
import com.fosu.software.delivery.domain.Products;
import com.fosu.software.delivery.service.impl.OrderFormServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    @GetMapping("/reserve")
    public Object selectReserveForm(){
        return orderFormService.selectReserveForm();
    }
    @PostMapping("/update")
    public Object updateOrderForm(@RequestBody Map<String,Object> map) {
        return orderFormService.updateOrderForm(map);
    }
    @GetMapping("/check")
    public Object orderCheck(){
        return orderFormService.orderCheck();
    }
    @GetMapping("/already")
    public Object alreadyPackage() {
        return orderFormService.alreadyPackage();
    }
    @GetMapping("/already/{num}")
    public Object selectAlreadyPackage(@PathVariable("num") String num) {
        return orderFormService.selectAlreadyPackage(num);
    }
    @PostMapping("/goBack/{num}")
    public Object goBackOrder(@PathVariable("num") String orderFormNumber){
        return orderFormService.goBackOrder(orderFormNumber);
    }
    @PostMapping("/warehouse")
    public Object insertWarehouse(@RequestBody Map<String,Object> map){
        return orderFormService.insertWarehouse(map);
    }
    @GetMapping("/warehousing")
    public Object warehousing(){
        return orderFormService.selectWarehouse();
    }
    @PostMapping("/output")
    public Object putOrder(@RequestBody Map<String,Object> map){
        return orderFormService.putOrder(map);
    }
    @GetMapping("/selectedOutput")
    public List<Map> selectedWarehouse(){
        return orderFormService.selectedWarehouse();
    }
    // 出库信息显示
    @GetMapping("/outputWarehouse")
    public Object outputWarehouse(){
        return orderFormService.outputWarehouse();
    }
}
