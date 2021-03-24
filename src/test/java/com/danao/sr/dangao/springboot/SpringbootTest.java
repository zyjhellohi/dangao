package com.danao.sr.dangao.springboot;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.danao.sr.service.DangaoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootTest {


    @Autowired
    DangaoService dangaoService;

    @Autowired(required = false)
    private RestTemplate restTemplate;


    @Test
    public void bootTest(){
        System.out.println(dangaoService.query("12"));
    }


    /**
     * {
     *     "HeWeather6": [
     *         {
     *             "basic": {
     *                 "cid": "CN101200101",
     *                 "location": "武汉",
     *                 "parent_city": "武汉",
     *                 "admin_area": "湖北省",
     *                 "cnty": "中国",
     *                 "lat": "30.5843544",
     *                 "lon": "114.29856873",
     *                 "tz": "+8.00"
     *             },
     *             "update": {
     *                 "loc": "2021-03-23 23:17",
     *                 "utc": "2021-03-23 15:17"
     *             },
     *             "status": "ok",
     *             "now": {
     *                 "cloud": "91",
     *                 "cond_code": "101",
     *                 "cond_txt": "多云",
     *                 "fl": "11",
     *                 "hum": "72",
     *                 "pcpn": "0.0",
     *                 "pres": "1025",
     *                 "tmp": "11",
     *                 "vis": "16",
     *                 "wind_deg": "0",
     *                 "wind_dir": "北风",
     *                 "wind_sc": "0",
     *                 "wind_spd": "0"
     *             }
     *         }
     *     ]
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




}
