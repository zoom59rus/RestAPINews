package com.news.rest.dto.converter;

import com.news.rest.dto.CategoriesDto;
import com.news.rest.dto.NewsFullDto;
import com.news.rest.dto.NewsShortDto;
import com.news.rest.model.News;
import com.news.rest.model.NewsCategory;

import java.util.List;
import java.util.stream.Collectors;

public class NewsDtoConverter {

    public static CategoriesDto fromCategories(List<NewsCategory> categories){
        CategoriesDto categoriesDto = new CategoriesDto();
        categoriesDto.setNewsCategory(categories);

        return categoriesDto;
    }

    public static NewsShortDto fromNews(News news){
        NewsShortDto newsShortDto = new NewsShortDto();
        newsShortDto.setId(news.getId());
        newsShortDto.setTitle(news.getTitle());
        newsShortDto.setDate(news.getDate());
        newsShortDto.setShortDescription(news.getShortDescription());

        return newsShortDto;
    }

    public static List<NewsShortDto> fromNewsList(List<News> news){
        return news
                .stream()
                .map(NewsDtoConverter::fromNews)
                .collect(Collectors.toList());
    }

    public static NewsFullDto fromNewsByFull(News news){
        NewsFullDto result = new NewsFullDto();
        result.setId(news.getId());
        result.setTitle(news.getTitle());
        result.setDate(news.getDate());
        result.setShortDescription(news.getShortDescription());
        result.setFullDescription(news.getFullDescription());

        return result;
    }
}