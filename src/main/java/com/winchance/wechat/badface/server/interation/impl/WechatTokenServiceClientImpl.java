package com.winchance.wechat.badface.server.interation.impl;

import javax.jws.WebService;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;

import org.springframework.beans.factory.annotation.Autowired;

import com.mkingzhu.wechat.center.server.webservice.facade.WechatTokenService;
import com.winchance.wechat.badface.server.interation.WechatTokenServiceClient;

@WebService
public class WechatTokenServiceClientImpl implements WechatTokenServiceClient {
    @Autowired
    private WechatTokenService wechatTokenService;

    @Override
    public String getAppId() {
        return wechatTokenService.getAppId();
    }

    @Override
    public String getAccessToken() {
        try {
            return wechatTokenService.getAccessToken();
        } catch (WxErrorException e) {
            return "";
        }
    }

    @Override
    public String getJsapiTicket() {
        try {
            return wechatTokenService.getJsapiTicket();
        } catch (WxErrorException e) {
            return "";
        }
    }

    @Override
    public WxJsapiSignature createJsapiSignature(String url) {
        try {
            return wechatTokenService.createJsapiSignature(url);
        } catch (WxErrorException e) {
            return new WxJsapiSignature();
        }
    }
}
