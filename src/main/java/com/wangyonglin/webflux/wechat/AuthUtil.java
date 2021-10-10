package com.wangyonglin.webflux.wechat;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil implements InitializingBean {
    @Value("${wangyonglin.wechat.appid}")
    private String appId;

    @Value("${wangyonglin.wechat.mchid}")
    private String mchid;

    @Value("${wangyonglin.wechat.notify_url}")
    private String notifyUrl;

    public static  String APPID ;
    public static  String APPSECRET ;
    public static  String MCHID ;
    public static  String PATERNERKEY ;
    public static  String NOTIFYURL ;


    @Override
    public void afterPropertiesSet() throws Exception {
        APPID = appId;
        MCHID =mchid;
        NOTIFYURL = notifyUrl;
    }
}
