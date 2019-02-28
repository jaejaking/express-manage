package com.eightbyte.service.impl;

import com.eightbyte.domain.*;
import com.eightbyte.mapper.*;
import com.eightbyte.service.ExpressService;
import com.eightbyte.service.UserService;
import com.eightbyte.util.ExpressOrderGeneratorUtil;
import com.eightbyte.vo.ExpressInfoVo;
import com.eightbyte.vo.ExpressSendVo;
import com.eightbyte.vo.TraceRecordCountVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    private ExpressInfoMapper expressInfoMapper;

    @Autowired
    private ClientInfoMapper clientInfoMapper;

    @Autowired
    private ExpressTraceRecordMapper traceRecordMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ExpressCarrierRelationMapper carrierRelationMapper;


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
                .isBusy(0)
                .createTime(new Date())
                .updateTime(new Date())
                .status(1)
                .build();
        int insertExpressResult = expressInfoMapper.insert(expressInfo);
        int insertExpressId = expressInfo.getId();
        log.info("插入快递信息表结果数:{},id:{}", insertExpressResult, insertExpressId);
        return 3;
    }

    @Override
    public List<ExpressInfo> searchUndeliverExpressInfos() {
        ExpressInfoExample expressInfoExample = new ExpressInfoExample();
        expressInfoExample.createCriteria().andStatusEqualTo(1);
        return expressInfoMapper.selectByExample(expressInfoExample);
    }

    @Override
    public List<ExpressInfoVo> searchExpressInfoVosByStatus(int status) {
        return expressInfoMapper.searchExpressInfoVosByStatus(status);
    }

    @Override
    public ExpressInfoVo searchExpressInfoVosById(int id) {
        return expressInfoMapper.searchExpressInfoVoById(id);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, transactionManager = "transactionManager", rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int updateExpressInfoStatus(int status, List<ExpressInfoVo> list) {
        int rst = 0;
        for (ExpressInfoVo expressInfoVo : list) {
            ExpressInfo expressInfo = new ExpressInfo();
            expressInfo.setId(expressInfoVo.getExpressId());
            expressInfo.setStatus(status);
            rst += expressInfoMapper.updateByPrimaryKeySelective(expressInfo);
        }

        return rst;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, transactionManager = "transactionManager", rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int insertExpressTraceRecord(List<ExpressTraceRecord> records) {
        int rst = 0;
        for (ExpressTraceRecord record : records) {
            rst += traceRecordMapper.insert(record);
        }
        return rst;
    }

    @Override
    public List<TraceRecordCountVo> selectEveryExpressRecordCount() {
        return traceRecordMapper.selectEveryExpressRecordCount();
    }

    @Override
    public ExpressTraceRecord selectMaxTraceRecord(int expressId) {
        return traceRecordMapper.selectMaxTraceRecord(expressId);
    }

    @Override
    public int updateExpressInfoSelectiveById(ExpressInfo expressInfo) {
        return expressInfoMapper.updateByPrimaryKeySelective(expressInfo);
    }

    @Override
    public ExpressInfo selectExpressInfoById(int id) {
        return expressInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(ExpressTraceRecord record) {
        return traceRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<ExpressTraceRecord> searchTraceRecordByExpressId(int expressId) {
        ExpressTraceRecordExample expressTraceRecordExample = new ExpressTraceRecordExample();
        expressTraceRecordExample.createCriteria().andExpressIdEqualTo(expressId);
        expressTraceRecordExample.setOrderByClause("create_time desc");
        return traceRecordMapper.selectByExample(expressTraceRecordExample);
    }

    @Override
    public List<ExpressInfoVo> selectNoBusyExpressAnd4PickUp() {
        return expressInfoMapper.selectNoBusyExpressAnd4PickUp();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, transactionManager = "transactionManager", rollbackFor = Exception.class)
    public int assignExpress(String userName, int expressId) {
        User user = userService.selectUserByUserName(userName);
        if (user == null) {
            throw new RuntimeException("不存在此用户!");
        }

        ExpressCarrierRelation carrierRelation = ExpressCarrierRelation.builder()
                .expressId(expressId)
                .createTime(new Date())
                .updateTime(new Date())
                .userId(user.getId())
                .source(1)
                .build();

        int insert = carrierRelationMapper.insert(carrierRelation);
        log.info("relation insert:{}", insert);

        ExpressInfo expressInfo = expressInfoMapper.selectByPrimaryKey(expressId);
        expressInfo.setIsBusy(1);
        int update = expressInfoMapper.updateByPrimaryKeySelective(expressInfo);
        log.info("update express:{}", update);

        return insert + update;
    }

    @Override
    public List<ExpressInfoVo> searchExpressTaskByUserName(String userName) {
        User user = userService.selectUserByUserName(userName);
        if (user == null) {
            throw new RuntimeException("user 不存在!");
        }

        return expressInfoMapper.selectAllMyExpressTask(user.getId());
    }


}
