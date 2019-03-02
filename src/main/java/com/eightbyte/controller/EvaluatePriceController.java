package com.eightbyte.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eightbyte.util.AddrUtil;
import com.eightbyte.vo.CaculatePriceView;
import com.eightbyte.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/evaluatePrice")
public class EvaluatePriceController extends BaseController {

    private static Map<Integer, String> transferMap = new ConcurrentHashMap<>(4);

    @Value("${baidu.deveop.key}")
    private String key;

    static {
        transferMap.put(1, "飞机");
        transferMap.put(2, "火车");
        transferMap.put(3, "厢货");
        transferMap.put(4, "轮船");
    }

    @PostMapping("/start")
    public ResultVo evaluatePrice(String startAddr, String endAddr, Integer transferType, BigDecimal weight) {
        if (StringUtils.isBlank(startAddr) || StringUtils.isBlank(endAddr) || transferType == null || weight == null) {
            return error("参数错误！");
        }
        BigDecimal distance = AddrUtil.getDistaneBetween2Addr(startAddr, endAddr);
        BigDecimal price = calculateDistancePrice(distance).add(caculateTransferTypePrice(transferType)).add(calculateWeightPrice(weight));
        CaculatePriceView priceView = CaculatePriceView.builder()
                .price(price)
                .distance(distance)
                .days(calculateDays(distance, transferType))
                .transferType(transferType).build();

        logger.info("价格估算:{}", JSON.toJSONString(priceView));

        return success(priceView);
    }

    /**
     * 计算快递预估天数
     *
     * @param distance
     * @param transferType
     * @return
     */
    private Integer calculateDays(BigDecimal distance, Integer transferType) {
        if (distance == null || distance.doubleValue() <= 0) {
            return 0;
        }
        if (distance.doubleValue() <= 50) {
            return 1;
        }
        if (distance.doubleValue() <= 200) {
            return 2;
        }
        if (distance.doubleValue() <= 1000 && transferType != 1) {
            return 3;
        }
        if (distance.doubleValue() <= 1000 && transferType == 1) {
            return 1;
        }
        if (distance.doubleValue() <= 2000 && transferType == 1) {
            return 2;
        }
        return 4;
    }


    /**
     * 计算重量的价格
     *
     * @param weight
     * @return
     */
    private BigDecimal calculateWeightPrice(BigDecimal weight) {
        if (weight == null || weight.doubleValue() <= 0) {
            return BigDecimal.valueOf(0);
        }
        if (weight.doubleValue() <= 2) {
            return BigDecimal.valueOf(0);
        } else {
            return BigDecimal.valueOf((weight.doubleValue() - 2) * 3);
        }
    }

    /**
     * 设置路程价格
     *
     * @param distance
     * @return
     */
    private BigDecimal calculateDistancePrice(BigDecimal distance) {
        if (distance == null || distance.doubleValue() <= 0) {
            return BigDecimal.valueOf(0);

        }
        if (distance.doubleValue() <= 50) {
            return BigDecimal.valueOf(5);
        } else if (distance.doubleValue() <= 200) {
            return BigDecimal.valueOf(8);
        } else if (distance.doubleValue() <= 500) {
            return BigDecimal.valueOf(10);
        } else if (distance.doubleValue() <= 1000) {
            return BigDecimal.valueOf(15);
        }
        return BigDecimal.valueOf(20);
    }

    /**
     * 计算运输方式对应价格
     *
     * @param type
     * @return
     */
    private BigDecimal caculateTransferTypePrice(int type) {
        if (type == 1) {
            return BigDecimal.valueOf(10);
        }
        if (type == 2) {
            return BigDecimal.valueOf(2);
        }

        if (type == 3) {
            return BigDecimal.valueOf(0);
        }
        if (type == 4) {
            return BigDecimal.valueOf(4);
        }

        return BigDecimal.valueOf(0);
    }

}
