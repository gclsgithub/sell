package com.hytc.sellfood.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PRRAM_NOT_TRUE(1,"参数不正确"),

    PRODUCT_NOT_EXIT(10, "商品不存在"),
    PRODUCT_NOT_ENOUGH(11,"商品不足"),
    ORDER_NOT_EXIT(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIT(13,"订单详细不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确,订单已经支付"),
    UPDATE_ERROR(15,"更新失败"),
    ORDER_DETIAL_EMPTY(16,"订单详情为空"),
    CAR_EMPTY(17,"购物车不能为空"),
    OWNER_EXCEPTION(18,"用户失效")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
