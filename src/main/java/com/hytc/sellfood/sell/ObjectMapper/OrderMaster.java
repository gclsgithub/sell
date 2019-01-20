package com.hytc.sellfood.sell.ObjectMapper;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体
 */
@Data
@Entity
@DynamicUpdate
public class OrderMaster implements Serializable {


    private static final long serialVersionUID = -569974664605494920L;
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
    private Integer orderStatus = 0;

    /**
     * 支付状态默认0未支付
     * pay_status
     */
    private Integer payStatus = 0;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
