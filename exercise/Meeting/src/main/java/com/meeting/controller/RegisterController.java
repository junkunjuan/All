package com.meeting.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

/*import javax.servlet.http.HttpServletRequest;*/

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meeting.model.People;
import com.meeting.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String showRegister() {
		return "register";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(People people, ModelMap modelMap, HttpSession session) {
		People people2 = new People();
		people2 = userService.verify(people.getEmail());
		if(people2 == null) {
			userService.register(people);
			people.setId(userService.userId(people));
			userService.insertPicId(people);
			session.setAttribute("user", people);
			return "redirect:/";
		} else {
			modelMap.addAttribute("error", "该邮箱已注册过！");
		}
		return "register";
	}
}
