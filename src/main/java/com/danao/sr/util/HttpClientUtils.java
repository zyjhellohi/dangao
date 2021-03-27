package com.danao.sr.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.danao.sr.config.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

@Slf4j
@Component
public class HttpClientUtils {
    // 日志记录

    /**
     * 构建post请求
     *
     * @param requestParam
     * @return
     * @throws UnsupportedEncodingException
     */
    public HttpPost getHttpPost(String requestParam,String url) throws UnsupportedEncodingException {
        StringEntity entity = new StringEntity(requestParam, Charset.forName("UTF-8"));
        entity.setContentType("application/json");//发送json数据需要设置contentType
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        return httpPost;
    }


    public  String getHttpResult(HttpResponse response) throws IOException {
        if (response.getStatusLine().getStatusCode() == 200) {
            //读返回数据
            String conResult = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(conResult);
            log.info("返回结果为:{}", jsonObject.toJSONString());
            return jsonObject.toJSONString();
        }else{
            return "Http接口报错: "+response.getStatusLine().getStatusCode();
        }
    }
}
