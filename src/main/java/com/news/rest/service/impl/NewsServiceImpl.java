package com.news.rest.service.impl;

import com.news.rest.dto.NewsShortDto;
import com.news.rest.model.News;
import com.news.rest.model.NewsCategory;
import com.news.rest.repositories.NewsRepository;
import com.news.rest.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<NewsCategory> getAllCategories() {
        return newsRepository.getAllCategories();
    }

    @Override
    public List<News> getNewsByCategoryId(Long categoryId) {
        return newsRepository.getNewsByCategoriesId(categoryId);
    }

    @Override
    public News getNewsById(Long id) {
        return newsRepository.getNewsById(id);
    }

    @Override
    public List<NewsShortDto> pageable(List<NewsShortDto> news, Integer page, Integer size) {
        if (news.isEmpty()){
            return news;
        }

        if (page == 0 || page < 0){
            return news.stream().limit(10).collect(Collectors.toList());
        }

        return news.stream()
                .skip(page * size)
                .limit(size)
                .collect(Collectors.toList());
    }
}