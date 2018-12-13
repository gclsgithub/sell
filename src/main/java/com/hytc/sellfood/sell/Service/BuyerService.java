package com.hytc.sellfood.sell.Service;

import com.hytc.sellfood.sell.Dto.OrderDto;

public interface BuyerService {

    /**
     * 查找一条订单详细
     *
     * @param openId
     * @param orderId
     * @return
     */
    OrderDto findOneOrder(String openId, String orderId);

    /**
     * 取消一条订单详细
     *
     * @param openId
     * @param orderId
     * @return
     */
    OrderDto cancleOrder(String openId, String orderId);
}
