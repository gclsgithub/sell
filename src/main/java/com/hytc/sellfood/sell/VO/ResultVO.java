package com.hytc.sellfood.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class  ResultVO <T> {

    /** 返回信息 */
    @JsonProperty("msg")
    private String msg;

    /** 返回编码 0：正常，其他一场*/
    @JsonProperty("code")
    private String code;

    /** 返回数据 */
    @JsonProperty("data")
    private T data;

    public ResultVO(String msg, String code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public ResultVO() {
    }
}
