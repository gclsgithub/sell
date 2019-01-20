package com.hytc.sellfood.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**
     * appId
     */
    private String myAppId;

    /**
     * myAppSecret
     */
    private String myAppSecret;

    /**
     * 匹配Id
     */
    private String mchId;

    /**
     * 匹配Key
     */
    private String mchKey;

    /**
     * 商户认证证书路径
     */
    private String keyPath;

    /**
     * 微信支付的异步通知地址
     */
    private String notifyUrl;

}
