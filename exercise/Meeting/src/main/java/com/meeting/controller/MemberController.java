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
public class MemberController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value =  "member_content", method = RequestMethod.GET)
	public String showPerson(HttpSession session, ModelMap modelMap) {
		People people = (People)session.getAttribute("user");
		if (people.getImgPath() == null) {
			people.setImgPath(userService.searchPic(people));
		}
		modelMap.addAttribute("user", people);
		return "member/member_content";
	}
	
	@RequestMapping(value =  "member_content", method = RequestMethod.POST)
	public String updateMember(People people, HttpSession session, ModelMap modelMap) {
		People people2 = (People)session.getAttribute("user");
		people.setEmail(people2.getEmail());
		userService.updateMember(people);
		people.setImgPath(people2.getImgPath());
		session.setAttribute("user", people);
		//modelMap.addAttribute("user", people);
		return "redirect:/member_content";
	}
}
