package com.lsk.service;

import java.util.List;

import com.lsk.model.WebApp;
import com.lsk.model.dto.WebAppDto;

public interface WebAppService {
	
	public void saveWebApp(WebApp webApp);

	public void updateWebApp(WebApp webApp);

	public WebAppDto getWebDtoWebApp(Integer id);

	public Integer getArticlesView();

	public boolean webAppNotNull();

	public List<WebApp> getWebAppDtos();
}
