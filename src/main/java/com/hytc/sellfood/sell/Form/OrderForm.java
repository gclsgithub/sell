package com.hytc.sellfood.sell.Form;

import com.hytc.sellfood.sell.Dto.CartDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必须输入")
    private String name;

    /**
     * 手机号码
     */
    @NotEmpty(message = "手机号必须输入")
    private String phone;

    /**
     * 收获地址
     */
    @NotEmpty(message = "收货地址必须输入")
    private String address;

    /**
     * 微信openId
     */
    @NotEmpty(message =  "微信Id必须输入")
    private String  openid;

    @NotEmpty(message = "购物车信息不能为空")
    private String items;
}
