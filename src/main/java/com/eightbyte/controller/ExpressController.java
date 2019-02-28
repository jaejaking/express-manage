package com.eightbyte.controller;

import com.alibaba.fastjson.JSON;
import com.eightbyte.constant.Constant;
import com.eightbyte.domain.ExpressTraceRecord;
import com.eightbyte.mapper.ExpressInfoMapper;
import com.eightbyte.service.ExpressService;
import com.eightbyte.util.RegexUtil;
import com.eightbyte.vo.*;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/express")
public class ExpressController extends BaseController {

    @Autowired
    private ExpressService expressService;

    @Autowired
    private ExpressInfoMapper expressInfoMapper;


    private CommonResult checkExpressSendInfo(ExpressSendVo expressSendVo) {
        CommonResult commonResult = CommonResult.createCommonResult();
        if (expressSendVo == null) {
            return commonResult.fail("后台构造参数实体失败！");
        }
        if (StringUtils.isBlank(expressSendVo.getSendName()) || StringUtils.isBlank(expressSendVo.getReceiveName())) {
            return commonResult.fail("收件人姓名或者发件人姓名为空！");
        }
        if (StringUtils.isBlank(expressSendVo.getSendProvince()) || StringUtils.isBlank(expressSendVo.getReceiveProvince())) {
            return commonResult.fail("收件人省份或者发件人省份为空！");
        }
        if (StringUtils.isBlank(expressSendVo.getSendCity()) || StringUtils.isBlank(expressSendVo.getReceiveCity())) {
            return commonResult.fail("收件人城市或者发件人城市为空！");
        }
        if (StringUtils.isBlank(expressSendVo.getReceiveDistrict()) || StringUtils.isBlank(expressSendVo.getSendDistrict())) {
            return commonResult.fail("发件人区域或者收件人区域为空！");
        }
        if (StringUtils.isBlank(expressSendVo.getSendDetailedAddr()) || StringUtils.isBlank(expressSendVo.getReceiveDetailedAddr())) {
            return commonResult.fail("收件人或者发件人详细地址为空！");
        }
        if (StringUtils.isBlank(expressSendVo.getSendPhone()) || StringUtils.isBlank(expressSendVo.getReceivePhone())) {
            return commonResult.fail("收件人或者发件人手机号为空！");
        }
        if (!RegexUtil.isMobile(expressSendVo.getSendPhone()) || !RegexUtil.isMobile(expressSendVo.getReceivePhone())) {
            return commonResult.fail("发件人或者收件人手机号格式错误！");
        }

        if (StringUtils.isBlank(expressSendVo.getSendIdNo())) {
            return commonResult.fail("发件人身份证号为空！");
        }
        if (!RegexUtil.isIdNum(expressSendVo.getSendIdNo())) {
            return commonResult.fail("发件人身份证号格式错误！");
        }

        return commonResult.success("校验成功！");
    }

    private List<ExpressRecordView> getExpressRecordView(ParamBean paramBean) {
        List<ExpressInfoVo> expressInfoVos = expressInfoMapper.selectExpressInfoVosByParamBean(paramBean);
        List<ExpressRecordView> viewList = new ArrayList<>(8);
        for (ExpressInfoVo expressInfoVo : expressInfoVos) {
            ExpressRecordView recordView = new ExpressRecordView();
            recordView.setExpressId(expressInfoVo.getExpressId());
            recordView.setOrderNo(expressInfoVo.getExpressOrderNo());
            recordView.setStatus(expressInfoVo.getStatus());
            List<ExpressTraceRecord> list = expressService.searchTraceRecordByExpressId(recordView.getExpressId());
            recordView.setRecords(list);
            viewList.add(recordView);
        }
        return viewList;
    }


    @PostMapping("/saveExpressSendInfo")
    public ResultVo saveExpressSendInfo(ExpressSendVo expressSendVo) {
        logger.info("保存快递发送信息参数:{}", JSON.toJSONString(expressSendVo));
        CommonResult commonResult = checkExpressSendInfo(expressSendVo);
        if (!"200".equalsIgnoreCase(commonResult.getCode())) {
            return error(commonResult.getReason());
        }
        expressService.saveExpressSendInfo(expressSendVo);

        return success("保存快递信息成功!");
    }

    @GetMapping("/searchExpressTraceRecord")
    public ResultVo searchExpressTraceRecord(String queryStr) {
        if (StringUtils.isBlank(queryStr)) {
            return error("参数错误！");
        }
        ParamBean paramBean = new ParamBean();
        List<ExpressRecordView> viewList = null;
        if (RegexUtil.isMobile(queryStr)) {
            paramBean.setMobile(queryStr);
            viewList = getExpressRecordView(paramBean);

        } else {
            paramBean.setOrderNo(queryStr);
            viewList = getExpressRecordView(paramBean);
        }

        return success("查询成功!", viewList);

    }


    /**
     * 展示待取货的快递
     *
     * @return
     */
    public ResultVo findExpress4PickUp() {
        List<ExpressInfoVo> expressInfoVos = expressService.selectNoBusyExpressAnd4PickUp();
        logger.info("待取货的快递:{}", JSON.toJSONString(expressInfoVos));
        return success(expressInfoVos);
    }

    /**
     * 快递员登录指派快递给自己
     *
     * @param expressId
     * @return
     */
    @GetMapping("/me/assign")
    public ResultVo assginExpress4Me(Integer expressId) {
        logger.info("指派给我:{}", expressId);
        if (expressId == null) {
            return error("参数错误!");
        }
        HttpSession session = request.getSession(true);
        Object attribute = session.getAttribute(Constant.LOGIN_SUCCESS_TOKEN);
        if (attribute == null || !(attribute instanceof String)) {
            return error("从session当前用户名失败!");
        }
        String userName = (String) attribute;

        expressService.assignExpress(userName, expressId);
        return success("指派成功!");
    }

    /**
     * 查找我需要派送的所有快递信息
     *
     * @return
     */
    @GetMapping("/me/searchMyExpressTask")
    public ResultVo searchMyAssignedExpressInfo() {
        HttpSession session = request.getSession(true);
        Object userObj = session.getAttribute(Constant.LOGIN_SUCCESS_TOKEN);
        if (userObj == null || !(userObj instanceof String)) {
            return error("从session中获取用户名失败!");
        }
        String userName = (String) userObj;
        List<ExpressInfoVo> expressInfoVos = expressService.searchExpressTaskByUserName(userName);
        logger.info("需派送的所有快递信息:{}", JSON.toJSONString(expressInfoVos));
        return success(expressInfoVos);
    }



}
