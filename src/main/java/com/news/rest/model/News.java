package com.news.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
//@Table(name = "news")
public class News {
    private Long id;
    private String title;
    private Date date;
    private String shortDescription;
    private String fullDescription;
}