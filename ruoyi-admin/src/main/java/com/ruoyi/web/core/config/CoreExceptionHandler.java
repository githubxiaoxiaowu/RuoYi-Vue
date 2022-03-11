package com.ruoyi.web.core.config;

import com.ruoyi.code.util.JSONUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//action切面
@ControllerAdvice
public class CoreExceptionHandler {
	static Logger logger = LoggerFactory.getLogger(CoreExceptionHandler.class);
	public static final String DEFAULT_ERROR_VIEW = "error/error";
    //异常切面
	/*@ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }*/
	// <1> 处理 form data方式调用接口校验失败抛出的异常
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public AjaxResult bindExceptionHandler(BindException e) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		List<String> collect = fieldErrors.stream()
				.map(o -> o.getDefaultMessage())
				.collect(Collectors.toList());
		return AjaxResult.error(JSONUtil.toJsonString(collect));
	}
	// <2> 处理 json 请求体调用接口校验失败抛出的异常
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public AjaxResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		List<String> collect = fieldErrors.stream()
				.map(o -> o.getDefaultMessage())
				.collect(Collectors.toList());
		return AjaxResult.error(JSONUtil.toJsonString(collect));
	}
	// <3> 处理单个参数校验失败抛出的异常
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public AjaxResult constraintViolationExceptionHandler(ConstraintViolationException e) {
		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
		List<String> collect = constraintViolations.stream()
				.map(o -> o.getMessage())
				.collect(Collectors.toList());
		return AjaxResult.error(JSONUtil.toJsonString(collect));
	}

	/*// 全局异常
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public AjaxResult globalException(Exception e) {
		logger.error("globalException ",e);
		return AjaxResult.error(JSONUtil.toJsonString("系统异常！请联系管理员"));
	}*/
		
}
