package com.hytc.sellfood.sell.ObjectMapper;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体
 */
@Data
@Entity
@DynamicUpdate
public class OrderMaster {

    @Id
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
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
