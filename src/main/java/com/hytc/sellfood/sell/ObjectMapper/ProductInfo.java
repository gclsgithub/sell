package com.hytc.sellfood.sell.ObjectMapper;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
@DynamicUpdate
@Data
public class ProductInfo {

    /**
     * 产品
     */
    @Id
    private String productId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productSinglePrice;

    /**
     * 商品库存
     */
    private Long productStock;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 商品图片
     */
    private String productIcon;

    /**
     * 类别编号
     */
    private String categoeryType;

    /**
     * 删除flag  0 未删除 ，1 删除
     */
    private String delFlag;

    /**
     * 商品状态
     *0 下架 ，1上架
     */
    private Integer productStatus;

    public ProductInfo() {
    }

    public ProductInfo(String productName, BigDecimal productSinglePrice, Long productStock, String productDescription, String productIcon, String categoeryType) {
        this.productName = productName;
        this.productSinglePrice = productSinglePrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.categoeryType = categoeryType;
    }
}
