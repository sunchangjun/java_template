package hk.reco.music.iptv.common.ctrl;


import hk.reco.music.iptv.common.exception.IptvBusinessException;
import hk.reco.music.iptv.common.exception.IptvError;
import hk.reco.music.iptv.common.utils.JsonResult;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局的异常捕获
 * @author comelk
 * 2019.02.14
 */
@ControllerAdvice
@ResponseBody
public class ExceptionController {

	@ExceptionHandler({ Exception.class })
	public Object exception(Exception e) {
		e.printStackTrace();
		return new JsonResult(IptvError.SYSTEM_ERROR.msg);
	}

	@ExceptionHandler({ IptvBusinessException.class })
	public Object businessException(IptvBusinessException e) {
		return new JsonResult(e.getMsg());
	}

	/**
	 *
	 *  请求参数缺失异常统一处理
	 *
	 *
	 * @Author wangpq
	 * @Param [e]
	 * @Date 2019/6/6 11:11
	 * @return java.lang.Object
	 */
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	public Object businessException(MissingServletRequestParameterException e) {
		e.printStackTrace();
		return new JsonResult(IptvError.P_MISS.msg);
	}

	@ExceptionHandler(value = BindException.class)
	@ResponseBody
	public Object handleBindException(BindException e) {
		// ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
		e.printStackTrace();
		return new JsonResult(e.getFieldError().getDefaultMessage());
	}

	public static void main(String[] args) {
	}

}
