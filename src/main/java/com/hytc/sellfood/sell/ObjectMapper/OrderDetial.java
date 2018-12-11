package com.hytc.sellfood.sell.ObjectMapper;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@UpdateTimestamp
public class OrderDetial {

    /**
     * detialId
     */
    @Id
    private String detialId;

    /**
     * orderId
     */
    private String orderId;

    /**
     * product_id
     */
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal productSinglePrice;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    /**
     * 商品图片
     */
    private String productIcon;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}
