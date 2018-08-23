package cn.lsk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogController {
	@RequestMapping(value = "views/blogs")
	public String showBlogs() {
		
		return "blogs";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpServletRequest request, QueryCondition queryCondition){
//		return "index";
		return "index";
	}
	
	@RequestMapping(value = "views/admin")
	public String showAdmins() {
		
		return "hello";
	}
}
