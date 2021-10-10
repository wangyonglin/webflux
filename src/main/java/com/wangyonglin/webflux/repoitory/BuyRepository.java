package com.wangyonglin.webflux.repoitory;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangyonglin.webflux.library.Callback;
import com.wangyonglin.webflux.library.Magicwand;
import com.wangyonglin.webflux.wechat.AuthUtil;
import lombok.Getter;
import lombok.Setter;

public class BuyRepository {
    @Getter
    @Setter
    class BuyWechat {
        String appid;
        String mchid;
        String description;
        String out_trade_no;
        String notify_url;
        Amount amount = new Amount();
        Payer payer = new Payer();
    }

    @Getter
    @Setter
    class Amount {
        Integer total;
        String currency;
    }

    @Getter
    @Setter
    class Payer {
        String openid;
    }

    public void jackson(Callback<String> callback) {
        ObjectMapper objectMapper = new ObjectMapper();
        BuyWechat buy = new BuyWechat();
        buy.appid = AuthUtil.APPID;
        buy.mchid = AuthUtil.MCHID;
        buy.description = "#1";
        buy.out_trade_no = Magicwand.IdGenerator();
        buy.notify_url = AuthUtil.NOTIFYURL;
        buy.amount.total = 1;
        buy.amount.currency = "CNY";
        buy.payer.openid = "o4GgauInH_RCEdvrrNGrntXDuXXX";
        try {
            String out = objectMapper.writeValueAsString(buy);
            callback.resolve(out);
        } catch (JsonProcessingException e) {
            callback.reject(e);
        }


    }


}

