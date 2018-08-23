package com.meeting.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meeting.model.People;

@Controller
public class SearchController {
	
	@RequestMapping(value = "search_content", method = RequestMethod.GET)
	public String searchByMate(HttpSession session, ModelMap modelMap) {
		if(session != null)
			modelMap.addAttribute("user", (People)session.getAttribute("user"));
		return "member/search_content";
	}
}
