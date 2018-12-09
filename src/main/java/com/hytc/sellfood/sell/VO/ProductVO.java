package com.hytc.sellfood.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO <T> {

    /** 类别名 */
    @JsonProperty("name")
    private String productCategoeryName;

    /** 类别编号 */
    @JsonProperty("type")
    private  Integer ProductCategoeryType;

    /**  商品信息 */
    @JsonProperty("foods")
    private List<T> productInfo;
}
