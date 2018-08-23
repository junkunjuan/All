package com.lsk.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.StringUtil;
import com.lsk.constant.AttributeConstant;
import com.lsk.model.Category;
import com.lsk.model.dto.UserDto;
import com.lsk.service.CategoryService;

@Controller
@RequestMapping("manage/category")
public class ManageCategoryController {
	@Autowired
	private CategoryService categoryService;

	// 显示分类编辑页面
	@RequestMapping(method = RequestMethod.GET)
	public String showCategoryPage(HttpSession session, ModelMap modelMap) {
		modelMap.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
		modelMap.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
		modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "admin/category/editor.vm");
		return "admin/index";
	}

	// 创建分类
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createAction(String categoryName, ModelMap modelMap, HttpSession session) {
		modelMap.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
		modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "admin/category/editor.vm");
		if (StringUtil.isNotEmpty(categoryName)) {
			Category category = new Category();
			category.setName(categoryName);
			categoryService.saveCategory(category);
		} else {
			modelMap.addAttribute(AttributeConstant.ERROR, "分类名称未填写!");
		}
		modelMap.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
		return "admin/index";
	}

	// 显示 更新分类页面
	@RequestMapping(value = "/update/{categoryId:[0-9]+}", method = RequestMethod.GET)
	public String upDatePage(@PathVariable("categoryId") Integer categoryId, ModelMap modelMap, HttpSession session) {
		modelMap.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
		modelMap.addAttribute(AttributeConstant.MAIN_PAGE, "admin/category/editor.vm");
		Category category = categoryService.getCategory(categoryId);
		if (StringUtil.isNotEmpty(category.getName())) {
			modelMap.addAttribute(AttributeConstant.CATEGORY, categoryService.getCategory(categoryId));
		} else {
			modelMap.addAttribute(AttributeConstant.ERROR, "找不到该分类!");
		}
		modelMap.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
		return "admin/index";
	}

	// 更新分类
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String upDateAction(String categoryName, Integer categoryId, ModelMap model, HttpSession session) {
		model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
		model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/category/editor.vm");
		Category category = new Category();
		category.setName(categoryName);
		category.setId(categoryId);
		categoryService.updateCategory(category);
		model.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
		model.addAttribute(AttributeConstant.RETURN_INFO, "修改成功!");
		model.addAttribute(AttributeConstant.CATEGORY, category);
		return "admin/index";
	}

	// 删除分类
	@RequestMapping(value = "/delete/{categoryId:[0-9]+}")
	public String deleteAction(@PathVariable("categoryId") Integer categoryId, ModelMap model, HttpSession session) {
		model.addAttribute(AttributeConstant.USER, (UserDto) session.getAttribute(AttributeConstant.CURRENT_USER));
		model.addAttribute(AttributeConstant.MAIN_PAGE, "admin/category/editor.vm");
		Category category = categoryService.getCategory(categoryId);
		if (StringUtil.isNotEmpty(category.getName())) {
			categoryService.deleteCategory(categoryId);
			model.addAttribute(AttributeConstant.RETURN_INFO, "删除成功!");
		} else {
			model.addAttribute(AttributeConstant.ERROR, "找不到该分类");
		}
		model.addAttribute(AttributeConstant.CATEGORIES, categoryService.getCategories());
		return "admin/index";
	}
}
