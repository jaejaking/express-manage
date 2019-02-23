package com.eightbyte.vo;

import com.eightbyte.enumration.ResultCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommonResult {

    private String code;

    private String reason;

    private static final CommonResult COMMON_RESULT = new CommonResult();

    private CommonResult() {
    }

    public static CommonResult createCommonResult() {
        return COMMON_RESULT;
    }

    public CommonResult fail(String failReason) {
        return fail(ResultCode.ERROR.getCode(), failReason);
    }

    public CommonResult fail(String code, String failReason) {
        this.code = code;
        this.reason = failReason;
        return this;
    }

    public CommonResult success(String succesaReason) {
        return success(ResultCode.SUCCESS.getCode(), succesaReason);
    }

    public CommonResult success(String code, String successReason) {
        this.code = code;
        this.reason = successReason;
        return this;
    }
}
