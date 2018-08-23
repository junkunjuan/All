package com.lsk.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.StringUtil;
import com.lsk.constant.AttributeConstant;
import com.lsk.model.Category;
import com.lsk.model.dto.ArticleLiteDto;
import com.lsk.model.dto.CategoryDto;
import com.lsk.service.ArticleService;
import com.lsk.service.CategoryService;
import com.lsk.service.WebAppService;

@Controller
@RequestMapping("category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private WebAppService webAppService;
	
	//全部分类
	@RequestMapping(value = "list")
	public String list(ModelMap modelMap) {
		modelMap.addAttribute(AttributeConstant.WEB_APP_DTO, webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()));
		List<CategoryDto> categories = categoryService.getCategories();	
		modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "user/category/categoryList.vm");
		modelMap.addAttribute(AttributeConstant.CATEGORIES, categories);
		return "index";
	}
	
	//详情
	@RequestMapping("{categoryId:[0-9]+}")
	public String detail(@PathVariable("categoryId") Integer categoryId, ModelMap modelMap) {
		modelMap.addAttribute(AttributeConstant.WEB_APP_DTO, webAppService.getWebDtoWebApp(webAppService.getWebAppDtos().get(0).getId()));
		Category category = categoryService.getCategory(categoryId);
		modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "user/category/detail.vm");
		if(StringUtil.isNotEmpty(category.getName())) {
			List<ArticleLiteDto> articles = articleService.getArticlesByCategory(categoryId);
			if(articles.size() == 0)
				articles = null;
			modelMap.addAttribute(AttributeConstant.CATEGORY, category);
			modelMap.addAttribute(AttributeConstant.ARTICLES, articles);
		} else {
			modelMap.addAttribute(AttributeConstant.ERROR, "找不到该分类");
		}
		return "index";
	}
}
