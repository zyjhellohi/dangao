package com.danao.sr.dangao.springboot;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.danao.sr.config.RedisUtil;
import com.danao.sr.service.DangaoService;
import com.danao.sr.service.impl.WxCheckServiceImpl;
import com.danao.sr.util.HttpClientUtils;
import com.danao.sr.vo.Caidan;
import com.danao.sr.vo.ShouCaidan;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootTest {


    @Autowired
    DangaoService dangaoService;

    @Autowired(required = false)
    private RestTemplate restTemplate;

    @Autowired
    RedisUtil redisUtil;


    @Test
    public void bootTest() {
        System.out.println(dangaoService.query("12"));
    }


    /**
     * {
     * "HeWeather6": [
     * {
     * "basic": {
     * "cid": "CN101200101",
     * "location": "武汉",
     * "parent_city": "武汉",
     * "admin_area": "湖北省",
     * "cnty": "中国",
     * "lat": "30.5843544",
     * "lon": "114.29856873",
     * "tz": "+8.00"
     * },
     * "update": {
     * "loc": "2021-03-23 23:17",
     * "utc": "2021-03-23 15:17"
     * },
     * "status": "ok",
     * "now": {
     * "cloud": "91",
     * "cond_code": "101",
     * "cond_txt": "多云",
     * "fl": "11",
     * "hum": "72",
     * "pcpn": "0.0",
     * "pres": "1025",
     * "tmp": "11",
     * "vis": "16",
     * "wind_deg": "0",
     * "wind_dir": "北风",
     * "wind_sc": "0",
     * "wind_spd": "0"
     * }
     * }
     * ]
     * }
     */

//    @Test
//    public void test1() {
//
//        List<HttpMessageConverter<?>> httpMessageConverters = restTemplate.getMessageConverters();
//        httpMessageConverters.stream().forEach(httpMessageConverter -> {
//            if(httpMessageConverter instanceof StringHttpMessageConverter){
//                StringHttpMessageConverter messageConverter = (StringHttpMessageConverter) httpMessageConverter;
//                //设置编码为UTF-8
//                messageConverter.setDefaultCharset(Charset.forName("UTF-8"));
//            }
//        });
//
//        String json = restTemplate.getForObject("https://free-api.heweather.net/s6/weather/now?location=蕲春&key=3c3fa198cacc4152b94b20def11b2455",String.class);
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        JSONObject heWeather6 = (JSONObject) jsonObject.getJSONArray("HeWeather6").get(0);
//        if("ok".equals(heWeather6.getString("status"))){
//            JSONObject basic = heWeather6.getJSONObject("basic");
//            String address = basic.getString("cnty")+basic.getString("admin_area")+basic.getString("parent_city")+basic.getString("location");
//            JSONObject now = heWeather6.getJSONObject("now");
//            String condTxt = now.getString("cond_txt");
//            String windDir = now.getString("wind_dir");
//            String tmp = now.getString("tmp");
//            System.out.println(address);
//            System.out.println(condTxt);
//            System.out.println(windDir);
//            System.out.println(tmp);
//        }
//
//        Integer status = jsonObject.getInteger("status");
//        if (status == 200) {
//            JSONObject data = jsonObject.getJSONObject("data");
//            Double pm25 = data.getDouble("pm25");
//            String quality = data.getString("quality");
//            JSONArray forecast = data.getJSONArray("forecast");
//            for (int i = 0; i < forecast.size(); i++) {
//                JSONObject dayInfo = forecast.getJSONObject(i);
//                Integer date = dayInfo.getInteger("date");
//                if (date == 16) {
//                    String high =  dayInfo.getString("high");
//                    String low =  dayInfo.getString("low");
//                    String type = dayInfo.getString("type");
//                    String fx = dayInfo.getString("fx");
//                    System.out.println(pm25 + " " + quality + " " + high + " " + low
//                            + " " + type + " " + fx);
//                }
//            }
//        }
//    }
    @Autowired
    private HttpClientUtils httpClientUtils;

    @Autowired
    private HttpClient httpClient;

    private String toToken = "43_YHdy8ftD1LgtXHaXrC169lOynxQdy5T2DEUTVEzuXY2qiFre_8LamnrEtE1Gsl8k6IvQ6oRToT1RO23_2MoLzhJ_R19qlAXZ9lr7bF8egfLXHbHzBp-ny3zhs_Eb2uM7pfPw0PIV7PGKa27lXOBhAIAJAN";

    @Test
    public void getToken() throws IOException {
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxbf3a2df06f576f15&secret=6bbccab4a20755fff12a253004bb46cd");
//        HttpGet httpGet = new HttpGet("https://free-api.heweather.net/s6/weather/now?location=蕲春&key=3c3fa198cacc4152b94b20def11b2455");
        HttpResponse execute = httpClient.execute(httpGet);
        String access_token = JSONObject.parseObject(httpClientUtils.getHttpResult(execute)).getString("access_token");
        System.out.println(access_token);
        if(StringUtils.isNotEmpty(access_token))redisUtil.set("token",access_token,7200);
    }

    @Test
    public void deleteCaidan() throws IOException {
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + redisUtil.get("token"));
//        HttpGet httpGet = new HttpGet("https://free-api.heweather.net/s6/weather/now?location=蕲春&key=3c3fa198cacc4152b94b20def11b2455");
        HttpResponse execute = httpClient.execute(httpGet);
        System.out.println(httpClientUtils.getHttpResult(execute));
    }

    @Test
    public void createCaidan() throws IOException {
        Caidan caidan = new Caidan();
        List<ShouCaidan> list = new ArrayList<>();
        ShouCaidan shouCaidan = new ShouCaidan();
        shouCaidan.setKey("rselfmenu_0_0");
        shouCaidan.setName("订蛋糕");
        shouCaidan.setType("click");
        ShouCaidan shouCaidan1 = new ShouCaidan();
        shouCaidan1.setKey("rselfmenu_0_1");
        shouCaidan1.setName("在线下单");
        shouCaidan1.setType("click");
        ShouCaidan shouCaidan2 = new ShouCaidan();
        shouCaidan2.setUrl("https://router.map.qq.com/l?l=7yX9mSh");
        shouCaidan2.setName("找门店");
        shouCaidan2.setType("view");
//        ShouCaidan shouCaidan3 = new ShouCaidan();
//        shouCaidan3.setKey("rselfmenu_0_3");
//        shouCaidan3.setName("特别定制");
//        shouCaidan3.setType("click");
        list.add(shouCaidan);
        list.add(shouCaidan1);
        list.add(shouCaidan2);
//        list.add(shouCaidan3);


        List<ShouCaidan> list1 = new ArrayList<>();
        ShouCaidan shouCaidan4 = new ShouCaidan();
        shouCaidan4.setKey("rselfmenu_0_4");
        shouCaidan4.setName("wifi密码");
        shouCaidan4.setType("click");
        ShouCaidan shouCaidan5 = new ShouCaidan();
        shouCaidan5.setKey("rselfmenu_0_5");
        shouCaidan5.setName("抖音福利");
        shouCaidan5.setType("click");
//        ShouCaidan shouCaidan6 = new ShouCaidan();
//        shouCaidan6.setKey("rselfmenu_0_6");
//        shouCaidan6.setName("小点心系列");
//        shouCaidan6.setType("click");
        list1.add(shouCaidan4);
        list1.add(shouCaidan5);
//        list1.add(shouCaidan6);

//        List<ShouCaidan> list2 = new ArrayList<>();
//        ShouCaidan shouCaidan7 = new ShouCaidan();
//        shouCaidan7.setKey("rselfmenu_0_7");
//        shouCaidan7.setName("蛋糕小常识");
//        shouCaidan7.setType("click");
//        ShouCaidan shouCaidan8 = new ShouCaidan();
//        shouCaidan8.setKey("rselfmenu_0_8");
//        shouCaidan8.setName("面粉小常识");
//        shouCaidan8.setType("click");
//        ShouCaidan shouCaidan9 = new ShouCaidan();
//        shouCaidan9.setKey("rselfmenu_0_9");
//        shouCaidan9.setName("营养小常识");
//        shouCaidan9.setType("click");
//        ShouCaidan shouCaidan10 = new ShouCaidan();
//        shouCaidan10.setKey("rselfmenu_0_10");
//        shouCaidan10.setName("新闻小常识");
//        shouCaidan10.setType("click");
//        list2.add(shouCaidan7);
//        list2.add(shouCaidan8);
//        list2.add(shouCaidan9);
//        list2.add(shouCaidan10);

        ShouCaidan shouCaidan11 = new ShouCaidan();
        ShouCaidan shouCaidan12 = new ShouCaidan();
        ShouCaidan shouCaidan13 = new ShouCaidan();

        shouCaidan11.setName("产品购买");
        shouCaidan12.setName("领福利");
        shouCaidan13.setName("产品展示");
        shouCaidan13.setKey("rselfmenu_0_8");
        shouCaidan13.setType("click");

        shouCaidan11.setSub_button(list);
        shouCaidan12.setSub_button(list1);
//        shouCaidan13.setSub_button(list2);

        List<ShouCaidan> list3 = new ArrayList<>();
        list3.add(shouCaidan11);
        list3.add(shouCaidan12);
        list3.add(shouCaidan13);

        caidan.setButton(list3);

        HttpPost httpPost = httpClientUtils.getHttpPost(JSONObject.toJSONString(caidan), "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + redisUtil.get("token"));
        HttpResponse execute = httpClient.execute(httpPost);
        httpClientUtils.getHttpResult(execute);
    }

    @Autowired
    WxCheckServiceImpl wxCheckService;


    /**
     * 添加客服接口
     * {
     * "kf_account" : "test1@test",
     * "nickname" : "客服1",
     * "password" : "pswmd5"
     * }
     *
     * @throws IOException
     */

    @Test
    public void addCustomer() throws IOException {
        Map map = new HashMap<>();
        map.put("kf_account", "Jm009@Zlhsghp");
        map.put("nickname", "吉米蛋糕客服009");
        map.put("password", "123");
        System.out.println(JSONObject.toJSONString(map));

        httpComment(JSONObject.toJSONString(map), "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=");

    }

    @Test
    public void deleteCustomer() throws IOException {
        Map map = new HashMap<>();
        map.put("kf_account", "Zyj@Zlhsghp");
        httpComment(JSONObject.toJSONString(map), "https://api.weixin.qq.com/customservice/kfaccount/del?access_token=");

    }

    @Test
    public void queryOpenid() throws IOException {
        Map map = new HashMap<>();
        map.put("kf_account", "Zyj@Zlhsghp");
        httpComment(JSONObject.toJSONString(map), "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + toToken + "&next_openid=");

    }


    private void httpComment(String obj, String url) throws IOException {
        HttpPost httpPost = httpClientUtils.getHttpPost(obj, url);
        HttpResponse execute = httpClient.execute(httpPost);
        httpClientUtils.getHttpResult(execute);
    }


    /*@Test
    public void connectionRedis() {
        Jedis jedis = new Jedis("118.190.61.198");
        System.out.println(jedis.getClient().getPort());
        System.out.println("连接本地的Redis服务器成功");
        //查看服务是否运行
        System.out.println("服务正在运行：" + jedis.ping());
    }*/


    @Test
    public void testRedis() {
//        redisUtil.set("tes","nihaoma");
//        redisUtil.set("tes1","50fenzhogn",10);
//        redisUtil.set("tes2","50fenzhogn",20);
        redisUtil.del("tes");
    }

    @Test
    public void testRediss() {
        System.out.println(redisUtil.get("token"));
        System.out.println(redisUtil.get("tes1"));
        System.out.println(redisUtil.get("tes2"));
    }

    @Test
    public void queryUser() throws IOException {
        Map map = new HashMap<>();
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + redisUtil.get("token") + "&openid=oM-9u6D0iuUlGXWxd3l8HkaSpU4Y");
        HttpResponse execute = httpClient.execute(httpGet);
        System.out.println(httpClientUtils.getHttpResult(execute));
    }
}
