package com.wangyonglin.webflux.controller;

import com.wangyonglin.webflux.library.Callback;
import com.wangyonglin.webflux.repoitory.BuyRepository;
import com.wangyonglin.webflux.wechat.HttpsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController   {


    @RequestMapping(value="/hello", method = RequestMethod.GET) //网址
    public String hello(Model model, @RequestParam(value = "sn") String sn){

        BuyRepository wechatBuyRepository = new BuyRepository();
        wechatBuyRepository.jackson(new Callback<String>() {
            @Override
            public void resolve(String res) {
                model.addAttribute("content",res);
                HttpsUtil.buy(res, new Callback<String>() {
                    @Override
                    public void resolve(String res) {
                        System.out.println(res);
                    }

                    @Override
                    public void reject(Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

            }

            @Override
            public void reject(Exception e) {
                System.out.println(e.getMessage());
            }
        });

        return "hello"; /* 默认访问templates下面的.html页面 */
    }


}
