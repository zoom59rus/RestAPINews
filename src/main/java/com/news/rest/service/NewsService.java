package com.news.rest.service;


import com.news.rest.dto.NewsShortDto;
import com.news.rest.model.News;
import com.news.rest.model.NewsCategory;

import java.util.List;

public interface NewsService {

    List<NewsCategory> getAllCategories();
    List<News> getNewsByCategoryId(Long categoryId);
    News getNewsById(Long id);
    List<NewsShortDto> pageable(List<NewsShortDto> news, Integer page, Integer size);
}