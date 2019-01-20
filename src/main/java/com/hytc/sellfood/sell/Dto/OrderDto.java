package com.hytc.sellfood.sell.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hytc.sellfood.sell.ObjectMapper.OrderDetial;
import com.hytc.sellfood.sell.enums.OrderStatusEnum;
import com.hytc.sellfood.sell.enums.PayStatusEnum;
import com.hytc.sellfood.sell.serializer.LocalDateTimeGetTimeStamp;
import lombok.Data;
import utils.EnumUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    private String orderId;

    /**
     * 买家名
     */
    private String buyerName;

    /**
     * 买家电话
     */
    private String buyerTel;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openId
     */
    private String buyerOpenId;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态,默认0新下单
     */
    private Integer orderStatus;

    /**
     * 支付状态默认0未支付
     * pay_status
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeGetTimeStamp.class)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonSerialize(using = LocalDateTimeGetTimeStamp.class)
    private LocalDateTime updateTime;


    private List<OrderDetial> orderDetialList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtils.loopCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtils.loopCode(payStatus,PayStatusEnum.class);
    }


}
