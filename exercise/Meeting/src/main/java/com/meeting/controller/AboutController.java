package com.meeting.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meeting.model.People;

@Controller
public class AboutController {
	
	@RequestMapping(value = "about", method = RequestMethod.GET)
	public String about(ModelMap model, HttpSession session){
		model.addAttribute("user", (People)session.getAttribute("user"));
		return "about/about";
	}
	
	@RequestMapping(value = "contact", method = RequestMethod.GET)
	public String concat(ModelMap model, HttpSession session){
		model.addAttribute("user", (People)session.getAttribute("user"));
		return "about/contact";
	}
	
	@RequestMapping(value = "faq", method = RequestMethod.GET)
	public String faq(ModelMap model, HttpSession session){
		model.addAttribute("user", (People)session.getAttribute("user"));
		return "about/faq";
	}
	
	@RequestMapping(value = "services", method = RequestMethod.GET)
	public String service(ModelMap model, HttpSession session){
		model.addAttribute("user", (People)session.getAttribute("user"));
		return "about/services";
	}
}
