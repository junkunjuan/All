package com.meeting.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meeting.model.People;


@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model, HttpSession session, HttpServletRequest request) {
		if (session != null) {
			model.addAttribute("user", (People)session.getAttribute("user"));
		}
		return "index";
	}
}
