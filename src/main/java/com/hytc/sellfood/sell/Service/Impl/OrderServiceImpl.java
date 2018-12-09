package com.hytc.sellfood.sell.Service.Impl;

import com.hytc.sellfood.sell.Dto.OrderDto;
import com.hytc.sellfood.sell.Exception.ProductException;
import com.hytc.sellfood.sell.ObjectMapper.ProductInfo;
import com.hytc.sellfood.sell.Repository.ProductMapper;
import com.hytc.sellfood.sell.Service.OrderService;
import com.hytc.sellfood.sell.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {


        //查询商品的数量和价格
        orderDto.getOrderDetialList().parallelStream().forEach(
                info -> {
                    ProductInfo productInfo = productService.findOne(info.getProductId());
                    if (!ObjectUtils.isEmpty(productInfo)){

                    }else {
                        throw new ProductException( "数量或者价格不够");
                    }
                }
        );

        //计算总价格

        //写入订单数据库(orderDetial,orderMaster)

        //4.扣库存
        return null;
    }

    @Override
    public OrderDto findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDto> findList(String openId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto cancle(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto pay(OrderDto orderDto) {
        return null;
    }
}
