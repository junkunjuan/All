package com.lsk.model.dto;

public class WebAppDto {
	// id
	private Integer id;
	// 网站名称
	private String webName;
	// 网站标题
	private String webTitle;
	// 首页文章显示数量
	private Integer userPageArticleSize;
	// 管理员文章显示数量
	private Integer adminPageArticleSize;
	// 网站文章浏览总量
	private Integer articleViews;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getWebTitle() {
		return webTitle;
	}

	public void setWebTitle(String webTitle) {
		this.webTitle = webTitle;
	}

	public Integer getUserPageArticleSize() {
		return userPageArticleSize;
	}

	public void setUserPageArticleSize(Integer userPageArticleSize) {
		this.userPageArticleSize = userPageArticleSize;
	}

	public Integer getAdminPageArticleSize() {
		return adminPageArticleSize;
	}

	public void setAdminPageArticleSize(Integer adminPageArticleSize) {
		this.adminPageArticleSize = adminPageArticleSize;
	}

	public Integer getArticleViews() {
		return articleViews;
	}

	public void setArticleViews(Integer articleViews) {
		this.articleViews = articleViews;
	}
	
	 @Override
	 public String toString() {
		 return "WebAppDto{" +
	             "id=" + id +
	             ", webName='" + webName + '\'' +
	             ", webTitle='" + webTitle + '\'' +
	             ", userPageArticleSize=" + userPageArticleSize +
	             ", adminPageArticleSize=" + adminPageArticleSize +
	             ", articleViews=" + articleViews +
	             '}';
	    }
}
