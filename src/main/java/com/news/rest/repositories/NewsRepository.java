package com.news.rest.repositories;

import com.news.rest.model.News;
import com.news.rest.model.NewsCategory;

import java.util.List;

public interface NewsRepository {
    List<NewsCategory> getAllCategories();

    List<News> getNewsByCategoriesId(Long categoriesId);

    List<News> getAllNews();

    News getNewsById(Long id);
}