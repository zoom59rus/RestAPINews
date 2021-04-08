package com.news.rest.repositories.db;

import com.news.rest.model.News;
import com.news.rest.model.NewsCategory;
import com.news.rest.util.ParserForJson;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Component
public class Source {
    private final ParserForJson parserForJson;
    private final Map<Long, NewsCategory> base;

    public Source(ParserForJson parserForJson) {
        this.parserForJson = parserForJson;
        this.base = new HashMap<>();
    }

    @PostConstruct
    protected void init() {
        try {
            List<NewsCategory> categories = parserForJson.getCategoriesFromJson();
            List<News> news = parserForJson.getNewsFromJson();
            List<News> cat0 = news.stream().filter(n -> n.getId() != 19L).collect(Collectors.toList());
            List<News> cat1 = news.stream().filter(n -> n.getId() == 19L).collect(Collectors.toList());
            categories.stream().forEach(c -> {
                if (c.getId().equals(0L)) {
                    c.setNews(cat0);
                    base.put(0L, c);
                } else if (c.getId().equals(1L)) {
                    c.setNews(cat1);
                    base.put(1L, c);
                } else {
                    c.setNews(new ArrayList<>());
                    base.put(2L, c);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}