package com.wangyonglin.webflux.library;




import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Magicwand {
    public static String IdGenerator(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String time=sdf.format(new Date());
        String randomString = RandomStringUtils.randomNumeric(8);
        return time+randomString;
    }
    /**
     * 元转换成分
     * @param amount
     * @return
     */
    public static String getMoney(String amount) {
        if(amount==null){
            return "";
        }
        // 金额转化为分为单位
        // 处理包含, ￥ 或者$的金额
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if(index == -1){
            amLong = Long.valueOf(currency+"00");
        }else if(length - index >= 3){
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));
        }else if(length - index == 2){
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);
        }else{
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");
        }
        return amLong.toString();
    }

}
