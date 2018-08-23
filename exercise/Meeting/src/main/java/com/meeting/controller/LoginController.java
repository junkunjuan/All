package com.meeting.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meeting.model.People;
import com.meeting.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST) 
	public String login(People people, ModelMap modelMap, HttpSession session) {
		People people2 = new People();
		people2 = userService.login(people);
		if(people2 != null) {
			session.setAttribute("user", people2);
			return "redirect:/";
		} else {
			modelMap.addAttribute("error", "用户名或密码错误");
		}
		return "login";
	}
	
}
