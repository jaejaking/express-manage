package com.eightbyte.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * @author yanghaoran@ehomepay.com.cn
 * @description 距离计算工具类(依赖百度地图开放平台)
 */
public class AddrUtil {
    /**
     * 通过百度地图api获取指定地点经纬度
     *
     * @param addr
     * @return
     */
    public static double[] getCoordinate(String addr) {

        String lng = null;//经度  
        String lat = null;//纬度  
        String address = null;
        try {
            address = URLEncoder.encode(addr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=MbIsrwzPc8CDtyQYxHF2vo04cQw7yQvi" + "&address=" + address;
        URL myUrl = null;
        URLConnection connection = null;
        try {
            myUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader input = null;

        BufferedReader br = null;
        try {
            connection = (URLConnection) myUrl.openConnection();
            if (connection != null) {
                input = new InputStreamReader(connection.getInputStream(), "UTF-8");
                br = new BufferedReader(input);
                String data = "";
                while ((data = br.readLine()) != null) {
                    JSONObject jsonObject = JSONObject.parseObject(data);
                    lng = jsonObject.getJSONObject("result").getJSONObject("location").getString("lng");
                    lat = jsonObject.getJSONObject("result").getJSONObject("location").getString("lat");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new double[]{Double.parseDouble(lng), Double.parseDouble(lat)};
    }


    /**
     * 计算两地之间的空中距离
     *
     * @return km
     */
    public static BigDecimal getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 维度
        double lat1 = (Math.PI / 180) * latitude1;
        double lat2 = (Math.PI / 180) * latitude2;

        // 经度
        double lon1 = (Math.PI / 180) * longitude1;
        double lon2 = (Math.PI / 180) * longitude2;

        // 地球半径
        double R = 6371;

        // 两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;

        return BigDecimal.valueOf(d);
    }


    public static BigDecimal getDistaneBetween2Addr(String org, String end) {
        double[] orgArray = getCoordinate(org);
        double[] endArray = getCoordinate(end);
        return getDistance(orgArray[0], orgArray[1], endArray[0], endArray[1]);
    }

    private AddrUtil() {

    }
}
