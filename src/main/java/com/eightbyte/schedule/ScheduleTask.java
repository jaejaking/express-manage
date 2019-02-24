package com.eightbyte.schedule;

import com.eightbyte.service.ExpressService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author yanghaoran@ehomepay.com.cn
 * @createDate 2019/2/24
 * @description 定时任务类
 */
@Component
public class ScheduleTask implements InitializingBean {

    private List<Map<String, String>> addrList = new ArrayList<>(64);

    @Autowired
    private ExpressService expressService;

    /**
     * 模拟发货task,每隔两分钟执行一次
     */
    @Scheduled(cron = "0 */2 * * * ?")
    public void deliverGoodsTask() {


    }


    /**
     * 模拟快递运输中转,每十分钟执行1次
     */
    @Scheduled(cron = "0 */10 * * * ?")
    public void changeTransferStatus() {

    }


    /**
     * 模拟将已到达目的地快递状态变为待取货,每15分钟执行一次
     */
    @Scheduled(cron = "0 */15 * * * ?")
    public void changeArrived2Ready4Get() {

    }


    /**
     * 模拟将待取货变为已签收 每20分钟执行一次
     */
    @Scheduled(cron = "0 */20 * * * ?")
    public void changeReady4Get2Signed() {

    }


    @Override
    public void afterPropertiesSet() throws Exception {

        Map<String, String> mapFirst = new HashMap<>(3);

        Map<String, String> mapSecond = new HashMap<>(3);

        Map<String, String> mapThird = new HashMap<>(3);

        Map<String, String> mapFourth = new HashMap<>(3);

        Map<String, String> mapFiveth = new HashMap<>(3);

        Map<String, String> mapSixth = new HashMap<>(3);

        Map<String, String> mapEighth = new HashMap<>(3);

        Map<String, String> mapNineth = new HashMap<>(3);

        Map<String, String> mapTenth = new HashMap<>(3);


        mapFirst.put("province", "吉林省");
        mapFirst.put("city", "长春市");
        mapFirst.put("district", "南关区");

        mapSecond.put("province", "河南省");
        mapSecond.put("city", "郑州市");
        mapSecond.put("district", "中原区");

        mapFirst.put("province", "湖北省");
        mapFirst.put("city", "武汉市");
        mapFirst.put("district", "汉阳区");


        mapThird.put("province", "湖南省");
        mapThird.put("city", "长沙市");
        mapThird.put("district", "天心区");

        mapFourth.put("province", "广东省");
        mapFourth.put("city", "广州市");
        mapFourth.put("district", "花都区");

        mapFiveth.put("province", "海南省");
        mapFiveth.put("city", "海口市");
        mapFiveth.put("district", "秀英区");

        mapSixth.put("province", "重庆市");
        mapSixth.put("city", "重庆市");
        mapSixth.put("district", "万州区");

        mapEighth.put("province", "四川省");
        mapEighth.put("city", "成都市");
        mapEighth.put("district", "武侯区");

        mapNineth.put("province", "贵州省");
        mapNineth.put("city", "贵阳市");
        mapNineth.put("district", "南明区");

        mapThird.put("province", "陕西省");
        mapThird.put("city", "西安市");
        mapThird.put("district", "新城区");

        mapTenth.put("province", "甘肃省");
        mapTenth.put("city", "兰州市");
        mapTenth.put("district", "城关区");

        addrList.add(mapFirst);
        addrList.add(mapSecond);
        addrList.add(mapThird);
        addrList.add(mapFourth);
        addrList.add(mapFiveth);
        addrList.add(mapSixth);
        addrList.add(mapEighth);
        addrList.add(mapNineth);
        addrList.add(mapTenth);


    }
}
