package com.eightbyte.schedule;

import com.alibaba.fastjson.JSON;
import com.eightbyte.domain.ExpressInfo;
import com.eightbyte.domain.ExpressInfoExample;
import com.eightbyte.domain.ExpressTraceRecord;
import com.eightbyte.domain.ExpressTraceRecordExample;
import com.eightbyte.mapper.ExpressInfoMapper;
import com.eightbyte.mapper.ExpressTraceRecordMapper;
import com.eightbyte.service.ExpressService;
import com.eightbyte.vo.ExpressInfoVo;
import com.eightbyte.vo.TraceRecordCountVo;
import com.eightbyte.vo.TraceRecordVo;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author yanghaoran@ehomepay.com.cn
 * @createDate 2019/2/24
 * @description 定时任务类
 */
@Component
@Slf4j
public class ScheduleTask implements InitializingBean {

    private List<Map<String, String>> addrList = new ArrayList<>(64);

    private Random random = new Random();

    @Autowired
    private ExpressService expressService;


    @Autowired
    private ExpressTraceRecordMapper traceRecordMapper;

    @Autowired
    private ExpressInfoMapper expressInfoMapper;


    @Value("${max.record.count}")
    private Integer recordCount;


    private void chooseSuitableProvinceCity(ExpressTraceRecord record, ExpressInfoVo expressInfoVo) {
        List<String> history = new ArrayList<>(8);
        String deafultProvinceStr = "山东蓬莱岛";
        String sendProvinceCityStr = expressInfoVo.getSendProvince().concat(expressInfoVo.getSendCity());
        String receiveProvinceCityStr = expressInfoVo.getReceiveProvince().concat(expressInfoVo.getReceiveCity());

        for (; ; ) {
            Map<String, String> map = addrList.get(random.nextInt(addrList.size()));
            String princeCityStr = map.get("province").concat(map.get("city"));
            if (!history.contains(princeCityStr)) {
                if (princeCityStr != null && !princeCityStr.equalsIgnoreCase(sendProvinceCityStr) && !princeCityStr.equalsIgnoreCase(receiveProvinceCityStr)) {
                    record.setToAddr(princeCityStr);
                    break;
                }
                history.add(princeCityStr);
            }
            if (history.size() >= addrList.size()) {
                record.setToAddr(deafultProvinceStr);
                return;
            }
        }

    }

    private void chooseSuitable(ExpressTraceRecord record) {
        Set<String> history = new HashSet<>(8);
        String defaultProvinceStr = "山东蓬莱岛";
        String sendProvinceCityStr = record.getFromAddr();
        log.info("record:{}", JSON.toJSONString(record));
        for (; ; ) {
            Map<String, String> map = addrList.get(random.nextInt(addrList.size()));
            String princeCityStr = map.get("province").concat(map.get("city"));
            if (!history.contains(princeCityStr)) {
                if (princeCityStr != null && !princeCityStr.equalsIgnoreCase(sendProvinceCityStr) && (record.getHistoryAddr().indexOf(princeCityStr) == -1)) {
                    record.setToAddr(princeCityStr);
                    break;
                }
            }
            history.add(princeCityStr);
            if (history.size() >= addrList.size()) {
                record.setToAddr(defaultProvinceStr);
                return;
            }
        }

    }


    /**
     * 模拟发货task,每隔两分钟执行一次
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void deliverGoodsTask() {
        log.info("模拟快递发货开始！");
        List<ExpressInfoVo> expressInfoVos = expressService.searchExpressInfoVosByStatus(1);
        if (expressInfoVos.isEmpty()) {
            return;
        }
        List<ExpressTraceRecord> list = new ArrayList<>(8);
        for (ExpressInfoVo expressInfoVo : expressInfoVos) {
            ExpressTraceRecord expressTraceRecord = new ExpressTraceRecord();
            String sendProvinceCityStr = expressInfoVo.getSendProvince().concat(expressInfoVo.getSendCity());
            String receiveProvinceCityStr = expressInfoVo.getReceiveProvince().concat(expressInfoVo.getReceiveCity());

            expressTraceRecord.setHistoryAddr(sendProvinceCityStr.concat(receiveProvinceCityStr));
            expressTraceRecord.setExpressId(expressInfoVo.getExpressId());
            expressTraceRecord.setExpressOrderNo(expressInfoVo.getExpressOrderNo());
            expressTraceRecord.setFromAddr(expressInfoVo.getSendProvince().concat(expressInfoVo.getSendCity()));
            if (sendProvinceCityStr != null && sendProvinceCityStr.equalsIgnoreCase(receiveProvinceCityStr)) {
                expressTraceRecord.setToAddr(receiveProvinceCityStr);

            } else {
                chooseSuitableProvinceCity(expressTraceRecord, expressInfoVo);
            }
            expressTraceRecord.setIsTo(0);
            expressTraceRecord.setExpressStatus(0);
            expressTraceRecord.setCreateTime(new Date());
            expressTraceRecord.setUpdateTime(new Date());
            list.add(expressTraceRecord);
        }
        log.info("list:{}", JSON.toJSONString(list));
        int insertRst = expressService.insertExpressTraceRecord(list);

        int updateRst = expressService.updateExpressInfoStatus(2, expressInfoVos);
        log.info("insertRst:{}", insertRst);
        log.info("updateRst:{}", updateRst);
    }


    /**
     * 模拟快递运输中转,每十分钟执行1次
     */
    @Scheduled(cron = "0 */2 * * * ?")
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, transactionManager = "transactionManager", rollbackFor = Exception.class)
    public void changeTransferStatus() {
        log.info("模拟快递中转开始！");
        List<ExpressTraceRecord> list = new ArrayList<>(64);
        List<TraceRecordCountVo> traceRecordCountVos = expressService.selectEveryExpressRecordCount();
        for (TraceRecordCountVo recordCountVo : traceRecordCountVos) {
            if (recordCountVo.getRecordCount() > recordCount - 1) {
                continue;
            }
            //设置当前中转到站
            ExpressTraceRecord expressTraceRecord = expressService.selectMaxTraceRecord(recordCountVo.getExpressId());
            expressTraceRecord.setIsTo(1);
            expressTraceRecord.setUpdateTime(new Date());
            int rst = expressService.updateById(expressTraceRecord);
            log.info("updateById：{}", rst);


            ExpressTraceRecord record = new ExpressTraceRecord();
            record.setFromAddr(expressTraceRecord.getToAddr());
            expressTraceRecord.setFromAddr(record.getFromAddr());
            chooseSuitable(expressTraceRecord);
            record.setHistoryAddr(expressTraceRecord.getHistoryAddr().concat(expressTraceRecord.getToAddr()));
            record.setIsTo(0);
            record.setExpressStatus(0);
            record.setExpressId(expressTraceRecord.getExpressId());
            record.setExpressOrderNo(expressTraceRecord.getExpressOrderNo());
            record.setUpdateTime(new Date());
            record.setCreateTime(new Date());
            record.setToAddr(expressTraceRecord.getToAddr());

            list.add(record);

        }
        if (!list.isEmpty()) {
            int rst = expressService.insertExpressTraceRecord(list);
            log.info("批量插入:{}", rst);
        }
    }


    /**
     * 模拟将已到达目的地快递状态变为已到达,每15分钟执行一次
     */
    @Scheduled(cron = "0 */15 * * * ?")
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, transactionManager = "transactionManager", rollbackFor = Exception.class)
    public void changeArrived2Ready4Get() {
        log.info("改变快递状态为已到达开始！");
        List<TraceRecordCountVo> traceRecordCountVos = expressService.selectEveryExpressRecordCount();
        for (TraceRecordCountVo recordCountVo : traceRecordCountVos) {
            if (recordCountVo.getRecordCount() >= recordCount) {
                ExpressInfo expressInfo = new ExpressInfo();
                expressInfo.setId(recordCountVo.getExpressId());
                expressInfo.setStatus(3);
                expressInfo.setUpdateTime(new Date());
                expressService.updateExpressInfoSelectiveById(expressInfo);

                ExpressInfoVo exVo = expressService.searchExpressInfoVosById(recordCountVo.getExpressId());

                ExpressTraceRecord expressTraceRecord = expressService.selectMaxTraceRecord(recordCountVo.getExpressId());
                expressTraceRecord.setIsTo(1);
                expressTraceRecord.setToAddr(exVo.getReceiveProvince().concat(exVo.getReceiveCity()).concat(exVo.getReceiveDistrict()));
                expressTraceRecord.setHistoryAddr(expressTraceRecord.getHistoryAddr().concat(expressTraceRecord.getToAddr()));
                expressTraceRecord.setUpdateTime(new Date());
                expressTraceRecord.setExpressStatus(0);
                expressService.updateById(expressTraceRecord);


            }
        }
    }


    @Scheduled(cron = "0 */15 * * * ?")
    /**
     *已到达的快递改为待取货
     */
    public void changeExpress2Wait2Get() {
        changeExpressInfoStatus(3, 4);
    }


    /**
     * 模拟将待取货变为已签收 每20分钟执行一次
     */
    @Scheduled(cron = "0 */20 * * * ?")
    public void changeReady4Get2Signed() {
        changeExpressInfoStatus(4, 5);
    }

    private int changeExpressInfoStatus(int origin, int target) {
        ExpressInfoExample expressInfoExample = new ExpressInfoExample();
        expressInfoExample.createCriteria().andStatusEqualTo(origin);

        ExpressInfo expressInfo = new ExpressInfo();
        expressInfo.setStatus(target);
        return expressInfoMapper.updateByExampleSelective(expressInfo, expressInfoExample);
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
        Map<String, String> mapA = new HashMap<>(3);
        Map<String, String> mapB = new HashMap<>(3);
        Map<String, String> mapC = new HashMap<>(3);
        Map<String, String> mapD = new HashMap<>(3);

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
        mapA.put("province", "浙江省");
        mapA.put("city", "杭州市");
        mapA.put("district", "余杭区");
        mapB.put("province", "浙江省");
        mapB.put("city", "宁波市");
        mapB.put("district", "江东区");
        mapC.put("province", "云南省");
        mapC.put("city", "昆明市");
        mapC.put("district", "盘龙区");
        mapD.put("province", "山西省");
        mapD.put("city", "太原市");
        mapD.put("district", "小店区");

        addrList.add(mapFirst);
        addrList.add(mapSecond);
        addrList.add(mapThird);
        addrList.add(mapFourth);
        addrList.add(mapFiveth);
        addrList.add(mapSixth);
        addrList.add(mapEighth);
        addrList.add(mapNineth);
        addrList.add(mapTenth);
        addrList.add(mapA);
        addrList.add(mapB);
        addrList.add(mapC);
        addrList.add(mapD);


    }
}
