package com.eightbyte.service.impl;

import com.eightbyte.domain.ClientInfo;
import com.eightbyte.domain.ExpressInfo;
import com.eightbyte.mapper.ClientInfoMapper;
import com.eightbyte.mapper.ExpressInfoMapper;
import com.eightbyte.service.ExpressService;
import com.eightbyte.util.ExpressOrderGeneratorUtil;
import com.eightbyte.vo.ExpressSendVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    private ExpressInfoMapper expressInfoMapper;

    @Autowired
    private ClientInfoMapper clientInfoMapper;

    @Override
    @Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public int saveExpressSendInfo(ExpressSendVo expressSendVo) {
        ClientInfo sendClientInfo = ClientInfo.builder()
                .name(expressSendVo.getSendName())
                .phone(expressSendVo.getSendPhone())
                .certNum(expressSendVo.getSendIdNo())
                .province(expressSendVo.getSendProvince())
                .city(expressSendVo.getSendCity())
                .district(expressSendVo.getSendDistrict())
                .createTime(new Date())
                .updateTime(new Date())
                .detailedAddr(expressSendVo.getSendDetailedAddr())
                .build();

        ClientInfo receiveClientInfo = ClientInfo.builder()
                .name(expressSendVo.getReceiveName())
                .phone(expressSendVo.getReceivePhone())
                .province(expressSendVo.getReceiveProvince())
                .city(expressSendVo.getReceiveCity())
                .district(expressSendVo.getReceiveDistrict())
                .detailedAddr(expressSendVo.getReceiveDetailedAddr())
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        int insertSendRst = clientInfoMapper.insert(sendClientInfo);
        int insertReceiveRst = clientInfoMapper.insert(receiveClientInfo);
        int sendID = sendClientInfo.getId();
        int receiveID = receiveClientInfo.getId();
        log.info("插入发件人信息表结果数：{},id:{}", insertSendRst, sendID);
        log.info("插入收件人信息表结果数:{},id:{}", insertReceiveRst, receiveID);
        ExpressInfo expressInfo = ExpressInfo.builder()
                .type(expressSendVo.getGoodsType())
                .weight(expressSendVo.getEstimateWeight())
                .sendPeopleId(sendID)
                .receivePeopleId(receiveID)
                .orderNo(ExpressOrderGeneratorUtil.generateOrderNo())
                .createTime(new Date())
                .updateTime(new Date())
                .status(0)
                .build();
        int insertExpressResult = expressInfoMapper.insert(expressInfo);
        int insertExpressId = expressInfo.getId();
        log.info("插入快递信息表结果数:{},id:{}", insertExpressResult, insertExpressId);
        return 3;
    }
}
