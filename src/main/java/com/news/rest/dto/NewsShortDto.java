package com.news.rest.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NewsShortDto {
    private Long id;
    private String title;
    private Date date;
    private String shortDescription;
}