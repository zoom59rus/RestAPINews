package com.news.rest.dto;

import com.news.rest.model.NewsCategory;
import lombok.Data;

import java.util.List;

@Data
public class CategoriesDto {
    private List<NewsCategory> newsCategory;
}
