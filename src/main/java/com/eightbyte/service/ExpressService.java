package com.eightbyte.service;

import com.eightbyte.domain.ExpressInfo;
import com.eightbyte.domain.ExpressTraceRecord;
import com.eightbyte.vo.ExpressSendVo;

import java.util.List;

public interface ExpressService {

    int saveExpressSendInfo(ExpressSendVo expressSendVo);

    List<ExpressInfo> searchUndeliverExpressInfos();

    int updateExpressInfoStatus(int status, List<ExpressInfo> list);

    int insertExpressTraceRecord(List<ExpressTraceRecord> records);

}
