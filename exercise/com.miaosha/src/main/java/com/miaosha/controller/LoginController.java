package com.miaosha.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

//import org.apache.commons.lang3.StringUtils;
/*import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miaosha.result.Result;
import com.miaosha.service.MiaoshaUserService;
//import com.miaosha.util.ValidatorUtil;
import com.miaosha.vo.LoginVo;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	//private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	MiaoshaUserService miaoshaUserService;
	
	@RequestMapping("/to_login")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("/do_login")
	@ResponseBody
	public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
		//logger.info(loginVo.toString());
	/*	String passInput = loginVo.getPassword();
		String mobile = loginVo.getMobile();
		if(StringUtils.isEmpty(passInput)) {
			return Result.error(CodeMsg.PASSWORD_EMPTY);
		}
		if(StringUtils.isEmpty(mobile)) {
			return Result.error(CodeMsg.MOBILE_EMPTY);
		}
		if(!ValidatorUtil.isMobile(mobile)) {
			return Result.error(CodeMsg.MOBILE_ERROR);
		}
		CodeMsg cMsg = miaoshaUserService.login(loginVo);
		if(cMsg.getCode() == 0) {
			return Result.success(true);
		} else {
			return Result.error(cMsg);
		}*/
		
		miaoshaUserService.login(response, loginVo);
		return Result.success(true);
	}
}
