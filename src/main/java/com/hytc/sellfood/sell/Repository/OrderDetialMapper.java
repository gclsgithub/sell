package com.hytc.sellfood.sell.Repository;

import com.hytc.sellfood.sell.ObjectMapper.OrderDetial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetialMapper extends JpaRepository<OrderDetial,String> {

    /**
     * 一个订单中会包含多个商品是一对多的关系
     * @param orderId
     * @return
     */
    List<OrderDetial> findByOrderId(String orderId);
}
