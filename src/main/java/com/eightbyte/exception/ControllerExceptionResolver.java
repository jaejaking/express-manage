package com.eightbyte.exception;

import com.eightbyte.enumration.ResultCode;
import com.eightbyte.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yanghaoran@ehomepay.com.cn
 * @createDate 2019/2/20
 * @description 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionResolver {

    @ExceptionHandler
    @ResponseBody
    public ResultVo illegalArgumentExceptionResolver(IllegalArgumentException ex, HttpServletRequest request) {
        log.error("不合法变量异常！", ex);
        return new ResultVo(ResultCode.ERROR.getCode(), ex.getMessage());
    }


}
