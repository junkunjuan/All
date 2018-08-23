package com.lsk.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lsk.dao.ArticleDao;
import com.lsk.model.Article;
import com.lsk.model.dto.ArticleDto;
import com.lsk.model.dto.ArticleLiteDto;
import com.lsk.service.ArticleService;
import com.lsk.util.Pager;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private ArticleDao articleDao;

	@Override
	public List<ArticleDto> searchArticles(Article article) {
		List<ArticleDto> articleDtoList = null;
		try {
			articleDtoList = articleDao.search(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleDtoList;
	}

	@Override
	public List<ArticleDto> getPageArticles(Pager pager) {
		List<ArticleDto> articleDtoList = null;
		try {
			articleDtoList = articleDao.pagerAction(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleDtoList;
	}

	@Override
	public ArticleDto getArticle(Integer id) {
		ArticleDto articleDto = null;
		try {
			articleDto = articleDao.getArticleDto(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleDto;
	}

	@Override
	public ArticleLiteDto getPreArticle(Integer id) {
		ArticleLiteDto articleLiteDto = null;
		try {
			articleLiteDto = articleDao.getPreArticleDto(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleLiteDto;
	}

	@Override
	public ArticleLiteDto getNextArticle(Integer id) {
		ArticleLiteDto articleLiteDto = null;
		try {
			articleLiteDto = articleDao.getNextArticleDto(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleLiteDto;
	}

	@Override
	public List<ArticleLiteDto> getArticlesByCategory(int categoryId) {
		List<ArticleLiteDto> articleLiteDtos = null;
		try {
			articleLiteDtos = articleDao.getByCategory(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleLiteDtos;
	}

	@Override
	public List<ArticleLiteDto> getArchive() {
		List<ArticleLiteDto> articleLiteDtos = null;
		try {
			articleLiteDtos = articleDao.archive();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleLiteDtos;
	}

	@Override
	public void updateArticle(Article article) {
		try {
			articleDao.update(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateArt(Article article) {
		try {
			articleDao.updates(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void saveArticle(Article article) {
		try {
			articleDao.save(article);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteArticle(Integer id) {
		try {
			articleDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int count() {
		int count = 0;
		try {
			count = articleDao.count();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void updateClicks(Integer clicks, Integer id) {
		try {
			articleDao.updateArticleClicks(clicks, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
