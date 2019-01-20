package com.hytc.sellfood.sell.ObjectMapper;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@UpdateTimestamp
public class OrderDetial implements Serializable {

    private static final long serialVersionUID = -6509810321621090801L;
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
    private Date updateTime;
}
