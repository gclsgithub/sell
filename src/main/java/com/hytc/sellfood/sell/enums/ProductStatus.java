package com.hytc.sellfood.sell.enums;

import lombok.Getter;

@Getter
public enum ProductStatus {
    UP(1,"上架"),
    DOWN(0,"下架")
    ;
    private Integer code;

    private String message;

    ProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
