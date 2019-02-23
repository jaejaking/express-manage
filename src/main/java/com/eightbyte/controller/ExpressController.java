package com.eightbyte.controller;

import com.alibaba.fastjson.JSON;
import com.eightbyte.service.ExpressService;
import com.eightbyte.util.RegexUtil;
import com.eightbyte.vo.CommonResult;
import com.eightbyte.vo.ExpressSendVo;
import com.eightbyte.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/express")
public class ExpressController extends BaseController {

    @Autowired
    private ExpressService expressService;


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

}
