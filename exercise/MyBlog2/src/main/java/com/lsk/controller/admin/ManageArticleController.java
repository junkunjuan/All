package com.lsk.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.StringUtil;
import com.lsk.constant.AttributeConstant;
import com.lsk.model.Article;
import com.lsk.model.dto.ArticleDto;
import com.lsk.model.dto.UserDto;
import com.lsk.service.ArticleService;
import com.lsk.service.CategoryService;
import com.lsk.service.WebAppService;
import com.lsk.util.Pager;
import com.sun.tools.internal.ws.processor.model.Model;

@Controller
@RequestMapping("manage/article")
public class ManageArticleController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private WebAppService webAppService;
	
	//显示创建页面
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String showCreatePage(ModelMap modelMap, HttpSession session) {
		modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/editorArticle.vm");
		modelMap.addAttribute(AttributeConstant.USER, (UserDto)session.getAttribute(AttributeConstant.CURRENT_USER));
		modelMap.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
		return "admin/index";
	}
	
	//创建操作
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String createAction( ModelMap modelMap, HttpSession session, HttpServletRequest request) {
		Article article = new Article();
		String path;
		article.setClicks(0);
		article.setTitle((String)request.getParameter("title"));
		article.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
		article.setRemark((String)request.getParameter("remark"));
		article.setContent((String)request.getParameter("editorValue"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		article.setPubDate(formatter.format(new Date()));
		article.setUserId(1);
		if(StringUtil.isNotEmpty(article.getTitle()) && StringUtil.isNotEmpty(article.getContent()) && StringUtil.isNotEmpty(article.getRemark())) {
			articleService.saveArticle(article);
			path = "redirect:/manage/article";
		} else {
			modelMap.addAttribute(AttributeConstant.ERROR, "有未填选项,请核对后重新发布文章!");
			modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/editorArticle.vm");
			modelMap.addAttribute(AttributeConstant.USER, (UserDto)session.getAttribute(AttributeConstant.CURRENT_USER));
			modelMap.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
			path = "admin/index";
		}
		return path;
	}
	
	//显示文章列表
	@RequestMapping(method = RequestMethod.GET)
	public String showListArticle(ModelMap modelMap, @RequestParam(defaultValue = "1") Integer currentPage, HttpSession session) {
		Pager pager = new Pager(currentPage, webAppService.getWebAppDtos().get(0).getAdminPageArticleSize(), articleService.count());
		List<ArticleDto> articles = articleService.getPageArticles(pager);
		modelMap.addAttribute(AttributeConstant.ARTICLES, articles);
		modelMap.addAttribute(AttributeConstant.PAGER, pager);
		modelMap.addAttribute(AttributeConstant.USER, (UserDto)session.getAttribute(AttributeConstant.CURRENT_USER));
		modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/listArticle.vm");
		return "admin/index";
	}
	
	//通过 ID 显示更新文章页面
	@RequestMapping(value = "update/{articleId:[0-9]+}", method = RequestMethod.GET)
	public String upDateArticle(ModelMap modelMap, HttpSession session, @PathVariable("articleId") Integer articleId) {
		modelMap.addAttribute(AttributeConstant.USER, (UserDto)session.getAttribute(AttributeConstant.CURRENT_USER));
		ArticleDto articleDto = articleService.getArticle(articleId);
		if(StringUtil.isNotEmpty(articleDto.getTitle())) {
			modelMap.addAttribute(AttributeConstant.ARTICLE, articleDto);
			modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/editorArticle.vm");
			modelMap.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
		} else {
			Pager pager = new Pager(1, 10, articleService.count());
			List<ArticleDto> articles = articleService.getPageArticles(pager);
			modelMap.addAttribute(AttributeConstant.ERROR, "找不到该文章!");
			modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/listArticle.vm");
            modelMap.addAttribute(AttributeConstant.PAGER, pager);
            modelMap.addAttribute(AttributeConstant.ARTICLES, articles);
		}
		return "admin/index";
	}
	
	//通过ID更新文章 操作
	@RequestMapping(value = "update/{articleId:[0-9]+}", method = RequestMethod.POST)
	public String upDateArticleAction(ModelMap modelMap, @PathVariable("articleId") Integer articleId, HttpSession session, HttpServletRequest request) {
		Article article = new Article();
		String path;
		article.setTitle((String)request.getParameter("title"));
		article.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
		article.setRemark((String)request.getParameter("remark"));
		article.setContent((String)request.getParameter("editorValue"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		article.setPubDate(formatter.format(new Date()));
		article.setId(articleId);
		if(StringUtil.isNotEmpty(article.getTitle()) && StringUtil.isNotEmpty(article.getContent()) && StringUtil.isNotEmpty(article.getRemark())) {
			articleService.updateArt(article);
			path = "redirect:/manage/article";
		} else {
			modelMap.addAttribute(AttributeConstant.ERROR, "有未填选项,请核对后重新发布文章!");
			modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/editorArticle.vm");
			modelMap.addAttribute(AttributeConstant.USER, (UserDto)session.getAttribute(AttributeConstant.CURRENT_USER));
			modelMap.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
			path = "admin/index";
		}
		return path;
	}
	
	//通过ID删除文章
	@RequestMapping(value = "delete/{articleId:[0-9]+}")
	public String deleteArticle(@PathVariable("articleId") Integer articleId, @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage) {
		String path = "redirect:/manage/article";
		articleService.deleteArticle(articleId);
		if(currentPage != 1) 
			path = "redirect:/manage/article/?currentPage=" + currentPage;
		return path;
	}
	
	//搜索文章
	@RequestMapping("search")
	public String searchArticle(String content, ModelMap modelMap, HttpSession session) {
		Article article = new Article();
		article.setTitle(content);
		modelMap.addAttribute(AttributeConstant.USER, (UserDto)session.getAttribute(AttributeConstant.CURRENT_USER));
		List<ArticleDto> articleDtos = articleService.searchArticles(article);
		Pager pager = new Pager(1, 10, articleService.searchArticles(article).size());
		if (articleDtos.size() > 0) {
			modelMap.addAttribute(AttributeConstant.ARTICLES, articleDtos);
		} else {
			modelMap.addAttribute(AttributeConstant.ARTICLES, null);
		}
		modelMap.addAttribute(AttributeConstant.PAGER, pager);
		modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "admin/article/listArticle.vm");
		return "admin/index";
	}
}
