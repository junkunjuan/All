package com.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miaosha.domain.User;
import com.miaosha.rabbitmq.MQSender;
import com.miaosha.redis.RedisService;
import com.miaosha.redis.UserKey;
import com.miaosha.result.CodeMsg;
import com.miaosha.result.Result;
import com.miaosha.service.UserService;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	MQSender mqSender;
	
	@RequestMapping("/hello")
	@ResponseBody
	public Result<String> home() {
		return Result.success("hello"); 
	}
	
	@RequestMapping("/mq")
	@ResponseBody
	public Result<String> mq() {
		mqSender.sendMiaoshaMessage("hello");
		return Result.success("hello"); 
	}
	
	@RequestMapping("/helloError")
	@ResponseBody
	public Result<String> helloErro() {
		return Result.error(CodeMsg.SERVER_ERROR);
	}
	
	@RequestMapping("/thymeleaf")
	public String thymeleaf(Model model) {
		model.addAttribute("name", "ls");
		return "hello";
	}
	
	@RequestMapping("/db/get")
	@ResponseBody
	public Result<User> dbGet() {
		User user = userService.getById(1);
		return Result.success(user);
	}
	
	@RequestMapping("/redis/get")
	@ResponseBody
	public Result<User> redisGet() {
		User user = redisService.get(UserKey.getById, ""+1, User.class);
		return Result.success(user);
	}
	
	@RequestMapping("/redis/set")
	@ResponseBody
	public Result<Boolean> redisSet() {
		User user = new User();
		user.setId(1);
		user.setName("ll");
		redisService.set(UserKey.getById, "" + 1, user);
		return Result.success(true);
	}
}
