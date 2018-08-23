package com.lsk.service;

import java.util.List;

import com.lsk.model.Category;
import com.lsk.model.dto.CategoryDto;
import com.lsk.util.Pager;

public interface CategoryService {
	//获取所有分类
	public List<CategoryDto> getCategories();
	
	//更新分类
	public void updateCategory(Category category);
	
	//保存或添加分类
	public void saveCategory(Category category);
	
	// 删除分类
	public void deleteCategory(Integer categoryId);
	
	//获取分类
	public Category getCategory(int categoryId);
	
	//是否存在该categoryId
	public boolean exist(int categoryId);
	
	//分页查找
	public List<CategoryDto> getPageCategories(Pager pager);
	
	//获取总数
    public int getCount();
}
