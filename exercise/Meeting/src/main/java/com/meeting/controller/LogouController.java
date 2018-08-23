package com.meeting.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogouController {
	
	@RequestMapping(value = "logout", method = RequestMethod.GET) 
	public String logout(HttpSession session) {
		session.setAttribute("user", null);
		return "redirect:/";
	}
}
