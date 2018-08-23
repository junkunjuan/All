package com.lsk.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.StringUtil;
import com.lsk.constant.AttributeConstant;
import com.lsk.model.Article;
import com.lsk.model.dto.ArticleDto;
import com.lsk.model.dto.ArticleLiteDto;
import com.lsk.service.ArticleService;
import com.lsk.service.WebAppService;
import com.lsk.util.Pager;

/**
 * 访客 文章页面
 */
@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private WebAppService webAppService;

	// 显示 详细文章
	@RequestMapping("article/{articleId:[0-9]+}")
	public String showArticle(@PathVariable("articleId") Integer articleId, ModelMap modelMap) {
		ArticleDto articleDto = articleService.getArticle(articleId);
		modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "user/article/detail.vm");
		modelMap.addAttribute(AttributeConstant.WEB_APP_DTO,
				webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()));

		if (StringUtil.isNotEmpty(articleDto.getTitle())) {
			// 点击量+1
			articleService.updateClicks(articleDto.getClicks() + 1, articleDto.getId());

			// 更新一下用于显示
			articleDto.setClicks(articleDto.getClicks() + 1);

			// 获取上一篇文章
			ArticleLiteDto preArticle = articleService.getPreArticle(articleDto.getId());

			// 获取下一篇文章
			ArticleLiteDto nextArticle = articleService.getNextArticle(articleDto.getId());

			modelMap.addAttribute(AttributeConstant.ARTICLE, articleDto);
			modelMap.addAttribute("preArticle", preArticle);
			modelMap.addAttribute("nextArticle", nextArticle);
		} else {
			modelMap.addAttribute(AttributeConstant.ERROR, "没有此文章");
		}
		return "index";
	}
	
	//搜索操作
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(String content, ModelMap modelMap) {
		Article article = new Article();
		article.setTitle(content);
		modelMap.addAttribute(AttributeConstant.WEB_APP_DTO, webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()));
		List<ArticleDto> articleDtos = articleService.searchArticles(article);
		if(articleDtos.size() > 0) {
			Pager pager = new Pager(1, articleService.searchArticles(article).size(), articleService.searchArticles(article).size());
			modelMap.addAttribute(AttributeConstant.PAGER, pager);
			modelMap.addAttribute(AttributeConstant.ARTICLES, articleDtos);
			modelMap.addAttribute(AttributeConstant.RETURN_INFO, "搜索内容已经全部显示");
		} else {
			modelMap.addAttribute(AttributeConstant.RETURN_INFO, "没有找到该内容");
		}
		//搜索不分页
        modelMap.addAttribute("search","search");
        modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "user/article/articleList.vm");
        return "index";
	}
	
	//直接访问搜索跳转到首页
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String showSearch() {
		return "redirect:/";
	}
}
