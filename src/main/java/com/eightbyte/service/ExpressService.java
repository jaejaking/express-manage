package com.eightbyte.service;

import com.eightbyte.domain.ExpressInfo;
import com.eightbyte.domain.ExpressTraceRecord;
import com.eightbyte.vo.ExpressInfoVo;
import com.eightbyte.vo.ExpressSendVo;
import com.eightbyte.vo.TraceRecordCountVo;

import java.util.List;

public interface ExpressService {

    int saveExpressSendInfo(ExpressSendVo expressSendVo);

    List<ExpressInfo> searchUndeliverExpressInfos();

    List<ExpressInfoVo> searchExpressInfoVosByStatus(int status);

    ExpressInfoVo searchExpressInfoVosById(int id);

    int updateExpressInfoStatus(int status, List<ExpressInfoVo> list);

    int insertExpressTraceRecord(List<ExpressTraceRecord> records);

    List<TraceRecordCountVo> selectEveryExpressRecordCount();

    ExpressTraceRecord selectMaxTraceRecord(int expressId);

    int updateExpressInfoSelectiveById(ExpressInfo expressInfo);

    ExpressInfo selectExpressInfoById(int id);

    int updateById(ExpressTraceRecord record);


}
