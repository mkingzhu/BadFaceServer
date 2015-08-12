package com.winchance.wechat.badface.server.web.home.controller;

import javax.servlet.http.HttpServletRequest;

import me.chanjar.weixin.common.bean.WxJsapiSignature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.winchance.util.StringUtil;
import com.winchance.wechat.badface.server.interation.WechatTokenServiceClient;

@Controller
public class HomeController {
    @Autowired
    private WechatTokenServiceClient wechatTokenServiceClient;

    @RequestMapping(value = "ResultView.htm", method = { RequestMethod.GET })
    public String resultView(ModelMap modelMap, HttpServletRequest request) {
        String appId = wechatTokenServiceClient.getAppId();
        WxJsapiSignature wxJsapiSignature = wechatTokenServiceClient.createJsapiSignature(getUrl(request));
        modelMap.addAttribute("appId", appId);
        modelMap.addAttribute(wxJsapiSignature);
        return "home/ResultView";
    }

    @RequestMapping(value = "RuleView.htm", method = { RequestMethod.GET })
    public String ruleView(ModelMap modelMap, HttpServletRequest request) {
        String appId = wechatTokenServiceClient.getAppId();
        WxJsapiSignature wxJsapiSignature = wechatTokenServiceClient.createJsapiSignature(getUrl(request));
        modelMap.addAttribute("appId", appId);
        modelMap.addAttribute(wxJsapiSignature);
        return "home/RuleView";
    }

    @RequestMapping(value = "TakePhotoView.htm", method = { RequestMethod.GET })
    public String takePhotoView(ModelMap modelMap, HttpServletRequest request) {
        String appId = wechatTokenServiceClient.getAppId();
        WxJsapiSignature wxJsapiSignature = wechatTokenServiceClient.createJsapiSignature(getUrl(request));
        modelMap.addAttribute("appId", appId);
        modelMap.addAttribute(wxJsapiSignature);
        return "home/TakePhotoView";
    }

    private String getUrl(HttpServletRequest request) {
        StringBuffer url = new StringBuffer("http://ecrm.so");
        url.append(request.getRequestURI());
        String queryString = request.getQueryString();
        if (!StringUtil.isEmpty(queryString))
            url.append("?").append(queryString);
        return url.toString();
    }
}
