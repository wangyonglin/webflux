package com.wangyonglin.webflux.wechat;



import com.wangyonglin.webflux.library.Callback;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class HttpsUtil {

    public void auth(String url, final Callback<String> callback) {
        // 创建HttpClient实例
        HttpClient client = HttpClientBuilder.create().build();
        // 根据URL创建HttpGet实例
        HttpGet get = new HttpGet(url);
        // 执行get请求，得到返回体
        try {
            HttpResponse response = client.execute(get);
            // 判断是否正常返回
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 解析数据
                String data = EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
                callback.resolve(data);
            }
        } catch (IOException e) {
            callback.reject(e);
        }


    }


    public static void buy(String json, final Callback<String> callback) {
        // 创建HttpClient实例
        HttpClient client = HttpClientBuilder.create().build();
        // 根据URL创建HttpPost实例
        HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");
        try {
            StringEntity stringEntity = new StringEntity(json,
                    ContentType.APPLICATION_JSON);// 构造请求数据

            // 传入请求体
            post.setEntity(stringEntity);
            // 发送请求，得到响应体
            HttpResponse response = client.execute(post);
            // 判断是否正常返回
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析数据
                HttpEntity resEntity = response.getEntity();
                String data = EntityUtils.toString(resEntity);
                callback.resolve(data);
            } else {
                callback.reject(new Exception(String.valueOf(response.getStatusLine().getStatusCode())));
            }

        } catch (UnsupportedEncodingException e) {
            callback.reject(e);
        } catch (IOException e) {
            callback.reject(e);
        }

    }
}
