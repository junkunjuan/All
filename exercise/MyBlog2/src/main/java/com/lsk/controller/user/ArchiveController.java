package com.lsk.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lsk.constant.AttributeConstant;
import com.lsk.model.dto.ArticleLiteDto;
import com.lsk.service.ArticleService;
import com.lsk.service.WebAppService;

@Controller
@RequestMapping("archive")
public class ArchiveController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private WebAppService webAppService;
	
	//显示归档页面
	@RequestMapping(method = RequestMethod.GET)
	public String archive(ModelMap modelMap) {
		List<ArticleLiteDto> articleLiteDtos = articleService.getArchive();
		modelMap.addAttribute(AttributeConstant.WEB_APP_DTO,webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()));
		modelMap.addAttribute(AttributeConstant.ARTICLES, articleLiteDtos);
		modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "user/archive/detail.vm");
		return "index";
	}
}
