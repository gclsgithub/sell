package com.hytc.sellfood.sell.enums;

import lombok.Getter;

@Getter
public enum OrderDetailEnum {

    NEW(0,"新建"),
    NOT_PAY(0,"未支付"),
    IS_PAY(1,"已经支付"),
    IS_COMPLETE(1,"完成"),
    CANCLE(2,"取消")
    ;

    private Integer code;

    private String message;

    OrderDetailEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
