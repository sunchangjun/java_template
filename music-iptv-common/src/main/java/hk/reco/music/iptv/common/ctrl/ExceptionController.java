package hk.reco.music.iptv.common.ctrl;


import hk.reco.music.iptv.common.ctrl.stb.RestResponse;
import hk.reco.music.iptv.common.exception.IptvBusinessException;
import hk.reco.music.iptv.common.exception.IptvError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局的异常捕获
 *
 * @author comelk
 * 2019.02.14
 */
@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler({Exception.class})
    public Object exception(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        log.error("系统异常，请求地址:[{}]", request.getRequestURI());
        return RestResponse.e(IptvError.SYSTEM_ERROR);
    }

    @ExceptionHandler({IptvBusinessException.class})
    public Object businessException(IptvBusinessException e) {
        return RestResponse.e(IptvError.SYSTEM_ERROR.code, e.getMsg());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public Object businessException(MissingServletRequestParameterException e, HttpServletRequest request) {
        e.printStackTrace();
        log.error("缺少请求参数异常，请求地址:[{}]", request.getRequestURI());
        return RestResponse.e(IptvError.P_MISS);
    }

    @ExceptionHandler(value = BindException.class)
    public Object handleBindException(BindException e, HttpServletRequest request) {
        // 随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        e.getFieldError();
        log.error("参数效验异常，请求地址:[{}]", request.getRequestURI());
        return RestResponse.e(IptvError.SYSTEM_ERROR.code, e.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Object handleNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error("请求方式异常，请求地址:[{}]", request.getRequestURI());
        return RestResponse.e(IptvError.P_METHOD);
    }


}
