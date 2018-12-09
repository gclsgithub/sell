package com.hytc.sellfood.sell.Service;

import com.hytc.sellfood.sell.Dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    /**
     * <br> 创建订单.</br>
     */
    OrderDto create(OrderDto orderDto);

    /**
     * <br> 查询单个订单 </br>
     */
    OrderDto findOne(String orderId);

    /**
     * <br> 查询订单列表 </br>
     */
    Page<OrderDto> findList(String openId, Pageable pageable);

    /**
     * <br> 取消订单. </br>
     */
    OrderDto cancle(OrderDto orderDto);

    /**
     * <br> 完结订单. </br>
     */
    OrderDto finish(OrderDto orderDto);

    /**
     * <br> 支付订单. </br>
     */
    OrderDto pay(OrderDto orderDto);

}
