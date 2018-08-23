package com.lsk.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lsk.constant.AttributeConstant;
import com.lsk.model.dto.UserDto;
import com.lsk.model.dto.WebAppDto;
import com.lsk.service.WebAppService;

@Controller
@RequestMapping("manage")
public class ManageController {
	
	@Autowired
	private WebAppService webAppService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String manageHome(ModelMap modelMap, HttpSession session) {
		WebAppDto webAppDto = webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId());
		modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "admin/home/home.vm");
		modelMap.addAttribute(AttributeConstant.USER, (UserDto)session.getAttribute(AttributeConstant.CURRENT_USER));
		modelMap.addAttribute(AttributeConstant.WEB_APP_DTO, webAppDto);
		return "admin/index";
	}
}
