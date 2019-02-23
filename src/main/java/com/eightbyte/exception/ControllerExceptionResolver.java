package com.eightbyte.exception;

import com.eightbyte.enumration.ResultCode;
import com.eightbyte.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
@Component
public class ControllerExceptionResolver {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResultVo illegalArgumentExceptionResolver(IllegalArgumentException ex, HttpServletRequest request) {
        log.error("不合法变量异常！", ex);
        return new ResultVo(ResultCode.ERROR.getCode(), ex.getMessage());
    }

    /**
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler({MissingServletRequestParameterException.class, TypeMismatchException.class,
            ConversionFailedException.class})
    public ResultVo handleConversionFailedException(Exception e) {
        log.error("参数不正确:", e);
        return new ResultVo(ResultCode.WARN.getCode(), e.getMessage());
    }

    /**
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResultVo handleThrowable(Throwable e) {
        log.error("服务异常:", e);
        return new ResultVo(ResultCode.ERROR.getCode(), "服务异常！", e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVo handleException(Exception e) {
        log.error("服务器异常!", e);
        return new ResultVo(ResultCode.ERROR.getCode(), "服务器异常！", e.getMessage());

    }

}
