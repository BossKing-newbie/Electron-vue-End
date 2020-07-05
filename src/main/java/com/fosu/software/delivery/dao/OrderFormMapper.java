package com.fosu.software.delivery.dao;

import com.fosu.software.delivery.domain.OrderForm;
import com.fosu.software.delivery.domain.OrderInfo;
import com.fosu.software.delivery.domain.Products;
import com.fosu.software.delivery.domain.ReserveForm;
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
    public List<Map> selectOrderForm();
    // 查询产品列表
    public Products getProducts(String productsId);
    // 插入OrderForm表
    public int insertOrderForm(OrderForm orderForm);
    // 插入OrderInfo表
    public int insertOrderInfo(OrderInfo orderInfo);
    // 插入ReserveForm表
    public int insertReserveForm(ReserveForm reserveForm);
}
