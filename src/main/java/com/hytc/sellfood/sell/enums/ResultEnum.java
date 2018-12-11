package com.hytc.sellfood.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIT(10, "商品不存在"),
    PRODUCT_NOT_ENOUGH(11,"商品不足"),
    ORDER_NOT_EXIT(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIT(13,"订单详细不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    UPDATE_ERROR(15,"更新失败"),
    ORDER_DETIAL_EMPTY(16,"订单详情为空")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
