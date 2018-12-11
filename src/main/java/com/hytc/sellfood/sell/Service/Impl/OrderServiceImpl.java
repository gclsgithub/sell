package com.hytc.sellfood.sell.Service.Impl;

import com.hytc.sellfood.sell.Dto.CartDto;
import com.hytc.sellfood.sell.Dto.OrderDto;
import com.hytc.sellfood.sell.Exception.ProductException;
import com.hytc.sellfood.sell.Exception.SellException;
import com.hytc.sellfood.sell.ObjectMapper.OrderDetial;
import com.hytc.sellfood.sell.ObjectMapper.OrderMaster;
import com.hytc.sellfood.sell.ObjectMapper.ProductInfo;
import com.hytc.sellfood.sell.Repository.OrderDetialMapper;
import com.hytc.sellfood.sell.Repository.OrderMasterMapper;
import com.hytc.sellfood.sell.Repository.ProductMapper;
import com.hytc.sellfood.sell.Service.OrderService;
import com.hytc.sellfood.sell.Service.ProductService;
import com.hytc.sellfood.sell.enums.OrderDetailEnum;
import com.hytc.sellfood.sell.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import utils.KeyUtil;
import utils.MapperUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetialMapper orderDetialMapper;

    @Autowired
    private OrderMasterMapper orderMasterMapper;


    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {

        String oderId = KeyUtil.getUnqiueKey();

        //总价
        BigDecimal bigDecimal = new BigDecimal(BigInteger.ZERO);

        //查询商品的数量和价格
        for (OrderDetial info : orderDto.getOrderDetialList()) {
            ProductInfo productInfo = productService.findOne(info.getProductId());
            if (ObjectUtils.isEmpty(productInfo)) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIT);
            }

            //计算订单的总价 = 单价*数量
            bigDecimal = productInfo.getProductSinglePrice()
                    .multiply(new BigDecimal(info.getProductQuantity()))
                    .add(bigDecimal);

            //拷贝属性
            BeanUtils.copyProperties(productInfo, info);
            //写入订单数据库(orderDetial)
            info.setOrderId(oderId);

            //primaryKey
            info.setDetialId(KeyUtil.getUnqiueKey());
            orderDetialMapper.save(info);
        }

        //写入订单数据库(orderMaster)
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderId(oderId);
        orderMaster.setOrderAmount(bigDecimal);
        orderMaster.setPayStatus(0);
        orderMaster.setOrderStatus(0);
        orderMasterMapper.save(orderMaster);

        //4.扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetialList().parallelStream().map(oderDetial ->
        {
            CartDto car = new CartDto();
            car.setProductId(oderDetial.getProductId());
            car.setProductQuantity(String.valueOf(oderDetial.getProductQuantity()));
            return car;
        }).collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);

        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {

        OrderMaster orderMaster = orderMasterMapper.findById(orderId).get();

        if (ObjectUtils.isEmpty(orderMaster)) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }

        List<OrderDetial> orderDetialList = orderDetialMapper.findByOrderId(orderId);

        if (CollectionUtils.isEmpty(orderDetialList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIT);
        }

        OrderDto outputDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, outputDto);

        outputDto.setOrderDetialList(orderDetialList);

        return outputDto;
    }

    @Override
    public Page<OrderDto> findList(String openId, Pageable pageable) {

        //获得订单信息
        Page<OrderMaster> orderMasterPage = orderMasterMapper.findByBuyerOpenId(openId, pageable);

        List<OrderDto> orderDtos = MapperUtil.mapList2List(orderMasterPage.getContent(), OrderDto.class);

        Page<OrderDto> orderDtoPage = new PageImpl<OrderDto>(orderDtos, pageable, orderMasterPage.getTotalElements());

        return orderDtoPage;
    }

    @Override
    @Transactional
    public OrderDto cancle(OrderDto orderDto) {

        OrderMaster orderMaster = null;

        //查询订单状态
        if (!OrderDetailEnum.NEW.getCode().equals(orderDto.getOrderStatus())) {
            log.error("【取消订单】状态不正确 oderId ={}", orderDto.getOrderId());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderDto.setOrderStatus(OrderDetailEnum.CANCLE.getCode());

        try {
            orderMaster = MapperUtil.coverBean2AnotherBean(orderDto, OrderMaster.class);
        } catch (Exception e) {
            log.error("【取消订单】 工具类转换异常");
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }

        //修改订单状态
        OrderMaster resultOrderMaster = orderMasterMapper.save(orderMaster);

        if (ObjectUtils.isEmpty(resultOrderMaster)) {
            log.error("【取消订单】 更新失败 odeer=", orderMaster);
            throw new SellException(ResultEnum.UPDATE_ERROR);
        }

        //判断是否存在订单
        if (CollectionUtils.isEmpty(orderDto.getOrderDetialList())) {
            log.error("【取消订单】 无订单信息");
            throw new SellException(ResultEnum.ORDER_DETIAL_EMPTY);
        }

        //返还库存
        productService.increaseStock(orderDto.getOrderDetialList().parallelStream().map(e ->
        {
            CartDto cartDto = new CartDto();
            cartDto.setProductQuantity(String.valueOf(e.getProductQuantity()));
            cartDto.setProductId(e.getProductId());
            return cartDto;
        }).collect(Collectors.toList()));


        //退款
        if (OrderDetailEnum.IS_PAY.getCode().equals(orderDto.getPayStatus())) {
            //TODO
        }


        return orderDto;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        //查询订单状态,只有是新订单才可以改变状态
        OrderMaster orderMaster = null;

        if (!OrderDetailEnum.NEW.getCode().equals(orderDto.getOrderStatus())) {
            log.error("【完成订单】状态不正确 oderId ={}", orderDto.getOrderId());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderDto.setOrderStatus(OrderDetailEnum.IS_COMPLETE.getCode());
        //修改订单状态
        try {
            orderMaster = MapperUtil.coverBean2AnotherBean(orderDto, OrderMaster.class);
        } catch (Exception e) {
            log.error("【取消订单】 工具类转换异常");
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }

        orderMasterMapper.save(orderMaster);

        orderDto.setOrderStatus(orderMaster.getOrderStatus());
        return orderDto;
    }

    @Override
    public OrderDto pay(OrderDto orderDto) {
        //查询订单状态,只有是新订单才可以改变状态
        OrderMaster orderMaster = null;

        if (!OrderDetailEnum.NEW.getCode().equals(orderDto.getOrderStatus())) {
            log.error("【支付订单】状态不正确 oderId ={}", orderDto.getOrderId());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderDto.setOrderStatus(OrderDetailEnum.IS_COMPLETE.getCode());
        orderDto.setPayStatus(OrderDetailEnum.IS_PAY.getCode());
        //修改订单状态
        try {
            orderMaster = MapperUtil.coverBean2AnotherBean(orderDto, OrderMaster.class);
        } catch (Exception e) {
            log.error("【取消订单】 工具类转换异常");
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }

        orderMasterMapper.save(orderMaster);


        return orderDto;
    }
}
