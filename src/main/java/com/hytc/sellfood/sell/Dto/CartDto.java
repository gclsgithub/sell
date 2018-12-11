package com.hytc.sellfood.sell.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartDto  implements Serializable {

    private static final long serialVersionUID = 1952292953119960632L;
    /**
     * 商品唯一表示符号
     */
    private String productId;

    /**
     * 该商品的购买的数量
     */
    private String productQuantity;

}
