package com.news.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
//@Entity
//@Table(name = "news_categories")
public class NewsCategory implements Serializable {
    private Long id;
    private String name;
    @JsonIgnore
    private List<News> news;
}