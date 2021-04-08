package com.news.rest.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.news.rest.model.News;
import com.news.rest.model.NewsCategory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Component
public class ParserForJson {
    private Gson gson;
    private Type categoryType;
    private Type newsType;

    public ParserForJson() {
        this.gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
        this.categoryType = new TypeToken<List<NewsCategory>>() {
        }.getType();
        this.newsType = new TypeToken<List<News>>(){}.getType();
    }

    public List<NewsCategory> getCategoriesFromJson() throws IOException {
        String json = loadFromData("/Users/anton/JavaDev/RestAPINews/src/main/resources/categories.json");
        List<NewsCategory> categories = gson.fromJson(json, categoryType);

        return categories;
    }

    public List<News> getNewsFromJson() throws IOException {
        String json = loadFromData("/Users/anton/JavaDev/RestAPINews/src/main/resources/news.json");
        List<News> news = gson.fromJson(json, newsType);

        return news;
    }

    private String loadFromData(String path) throws IOException {

        try(InputStream is = new FileInputStream(path)){
            return IOUtils.toString(is, StandardCharsets.UTF_8);
        } catch (FileNotFoundException e){
            log.error("IN loadFromData - File on path \'{}\' not exist or not found. Initialise BD can't be completing.", path);
        }

        return null;
    }
}
