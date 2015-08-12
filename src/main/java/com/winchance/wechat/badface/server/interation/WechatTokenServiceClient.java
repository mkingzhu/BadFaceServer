package com.winchance.wechat.badface.server.interation;

import me.chanjar.weixin.common.bean.WxJsapiSignature;

public interface WechatTokenServiceClient {
    public String getAppId();

    public String getAccessToken();

    public String getJsapiTicket();

    public WxJsapiSignature createJsapiSignature(String url);
}
