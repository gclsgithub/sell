package com.hytc.sellfood.sell.Controller;

import com.hytc.sellfood.sell.Exception.SellException;
import com.hytc.sellfood.sell.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl")String returnUrl){
         log.info("进入方法");

        String url = "http://hytc.natapp1.cc/sell/wechat/userinfo";

        //方法调用
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE,"");

        return "redirect:"+redirectUrl;
    }

    @GetMapping("/userinfo")
    private String userinfo(@RequestParam("code")String code,
                          @RequestParam("state")String returnUrl){

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();

        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】");
            e.printStackTrace();
            throw new SellException(ResultEnum.WECHAT_MP_ERROR);
        }

        String openId = wxMpOAuth2AccessToken.getOpenId();


        return "redirect:"+returnUrl+"?openid="+openId;

    }
}
