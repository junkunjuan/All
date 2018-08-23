package com.lsk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lsk.constant.AttributeConstant;
import com.lsk.service.ArticleService;
import com.lsk.service.WebAppService;
import com.lsk.util.Pager;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private WebAppService webAppService;

	@RequestMapping(method = RequestMethod.GET)
	public String home(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex, Model model,
			HttpServletRequest request) {

		Pager pager = new Pager(pageIndex,
				webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()).getUserPageArticleSize(),
				articleService.count());
		model.addAttribute(AttributeConstant.MAIN_PAGE, "user/article/articlelist.vm");
		model.addAttribute(AttributeConstant.WEB_APP_DTO,
				webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()));
		model.addAttribute(AttributeConstant.ARTICLES, articleService.getPageArticles(pager));
		model.addAttribute(AttributeConstant.PAGER, pager);
		return "index";
	}
}
