package com.hytc.sellfood.sell.Service.Impl;

import com.hytc.sellfood.sell.Dto.OrderDto;
import com.hytc.sellfood.sell.Exception.SellException;
import com.hytc.sellfood.sell.Service.BuyerService;
import com.hytc.sellfood.sell.Service.OrderService;
import com.hytc.sellfood.sell.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOneOrder(String openId, String orderId) {
        return checkOwner(openId, orderId);
    }

    @Override
    public OrderDto cancleOrder(String openId, String orderId) {
        if (ObjectUtils.isEmpty(checkOwner(openId, orderId))) {
            log.error("用户失效");
            throw new SellException(ResultEnum.OWNER_EXCEPTION);
        }

        OrderDto input = new OrderDto();
        input.setOrderId(orderId);
        return orderService.cancle(input);

    }


    private OrderDto checkOwner(String openId, String opderId) {
        OrderDto orderDto = orderService.findOne(opderId);

        if (ObjectUtils.isEmpty(orderDto)) {
            return orderDto;
        }

        if (!openId.equalsIgnoreCase(orderDto.getBuyerOpenId())) {
            log.error("用户失效");
            throw new SellException(ResultEnum.OWNER_EXCEPTION);
        }

        return orderDto;
    }
}
