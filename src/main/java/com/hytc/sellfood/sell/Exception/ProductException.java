package com.hytc.sellfood.sell.Exception;

import com.hytc.sellfood.sell.enums.ResultEnum;

public class ProductException extends RuntimeException {

    private static final long serialVersionUID = -2865020171835434721L;

    private Integer code;

    public ProductException(ResultEnum msg) {
        super(msg.getMessage());
        this.code = msg.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
