package com.miaosha.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miaosha.result.CodeMsg;
import com.miaosha.result.Result;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
		
		if(e  instanceof GlobalException) {
			GlobalException exception = (GlobalException)e;
			return Result.error(exception.getCm());
		} else if(e instanceof BindException) {
			BindException exception = (BindException)e;
			List<ObjectError> errors = exception.getAllErrors();
			ObjectError error = errors.get(0);
			String msg = error.getDefaultMessage();
			return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
		} else {
			e.printStackTrace();
			return Result.error(CodeMsg.SERVER_ERROR);
		}
	}
}
