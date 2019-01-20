package com.hytc.sellfood.sell.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hytc.sellfood.sell.enums.ProductStatus;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import utils.EnumUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author gcl
 */
@Entity
@DynamicUpdate
@Data
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 9206280627966471523L;
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
    private Integer categoeryType;

    /**
     * 删除flag  0 未删除 ，1 删除
     */
    private String delFlag;

    /**
     * 商品状态
     *0 下架 ，1上架
     */
    private Integer productStatus;

    private Date createTime;

    private Date updateTime;

    public ProductInfo() {
    }

    public ProductInfo(String productName, BigDecimal productSinglePrice, Long productStock, String productDescription, String productIcon, Integer categoeryType) {
        this.productName = productName;
        this.productSinglePrice = productSinglePrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.categoeryType = categoeryType;
    }

    @JsonIgnore
    public ProductStatus getProductStatusEnum(){
        return EnumUtils.loopCode(productStatus,ProductStatus.class);
    }

}
