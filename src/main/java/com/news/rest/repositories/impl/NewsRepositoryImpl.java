package com.news.rest.repositories.impl;

import com.news.rest.model.News;
import com.news.rest.model.NewsCategory;
import com.news.rest.repositories.NewsRepository;
import com.news.rest.repositories.db.Source;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsRepositoryImpl implements NewsRepository {

    private final Source source;

    public NewsRepositoryImpl(Source source) {
        this.source = source;
    }

    public List<NewsCategory> getAllCategories() {
        return new ArrayList<>(source.getBase().values());
    }

    @Override
    public List<News> getNewsByCategoriesId(Long categoriesId) {
        return source.getBase().get(categoriesId).getNews();
    }

    @Override
    public List<News> getAllNews() {
        return source.getBase().values()
                .stream()
                .map(NewsCategory::getNews)
                .findFirst().orElse(new ArrayList<>());
    }

    @Override
    public News getNewsById(Long id) {
        return getAllNews()
                .stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElse(new News());
    }
}