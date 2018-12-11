package com.hytc.sellfood.sell.Exception;

import com.hytc.sellfood.sell.enums.ResultEnum;

public class SellException extends RuntimeException{

    private static final long serialVersionUID = -6048614861522293634L;
    private Integer code;

    public SellException(ResultEnum msg) {
        super(msg.getMessage());
        this.code = msg.getCode();
    }
}
