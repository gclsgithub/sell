package com.hytc.sellfood.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum implements CodeEnum {

    PRRAM_NOT_TRUE(1,"参数不正确"),
    BEAN_COVER_FAIL(2,"数据转换异常"),
    PRODUCT_ON_SUCCESS(7,"商品上架成功"),
    PRODUCT_OFF_SUCCESS(8,"商品下架成功"),
    PRODUCT_STATUS_ERROR(9,"商品状态不正确"),
    PRODUCT_NOT_EXIT(10, "商品不存在"),
    PRODUCT_NOT_ENOUGH(11,"商品不足"),
    PRDOUCT_UPDATE_SUCCESS(23,"商品更新成功"),
    ORDER_NOT_EXIT(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIT(13,"订单详细不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确,订单已经支付"),
    UPDATE_ERROR(15,"更新失败"),
    ORDER_DETIAL_EMPTY(16,"订单详情为空"),
    CAR_EMPTY(17,"购物车不能为空"),
    OWNER_EXCEPTION(18,"用户失效"),
    WECHAT_MP_ERROR(19,"微信认证失效"),
    WECHAT_SEND_ERROR(20,"微信回调异常"),
    ORDER_CANCLE_SUCCESS(21,"订单取消成功"),
    ORDER_SEARCH_SUCCESS(22,"订单查询成功"),
    LOGIN_FAIL(23,"登陆失败"),
    LOG_OUT_SUCCESS(24,"登出成功"),
    PRODUCT_CATEGOERY_QUERRY_FAIL(40,"商品类别查询失败"),
    PRODUCT_CATEGOERY_SAVE_FAIL(41,"商品类表显示失败"),
    PRODUCT_CATEGOERY_SAVE_SUCCESS(41,"商品类表显示失败")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
