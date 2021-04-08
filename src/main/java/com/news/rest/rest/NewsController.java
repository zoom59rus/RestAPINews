package com.news.rest.rest;

import com.google.gson.JsonObject;
import com.news.rest.dto.CategoriesDto;
import com.news.rest.dto.NewsShortDto;
import com.news.rest.dto.converter.NewsDtoConverter;
import com.news.rest.service.NewsService;
import com.news.rest.service.impl.NewsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "news-controller", description = "Получение категорий и новостей.")
@RequestMapping("/v1/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsServiceImpl newsService) {
        this.newsService = newsService;
    }

    @Operation(summary = "Implementation Notes", description = "Возвращает все категории сразу, без пейджинга")
    @ApiResponses({
            @ApiResponse(content = {@Content(schema = @Schema(implementation = CategoriesDto.class))})
    })
    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCategories() {
        JSONObject response = new JSONObject();
        response.put("code", 0);
        response.put("list", NewsDtoConverter.fromCategories(newsService.getAllCategories()));

        return ResponseEntity.ok(response.toMap());
    }

    @Operation(summary = "Implementation Notes", description = "Возвращает список новостей в указанной категории, в коротком виде (без полного текста), с пейджингом (размер страницы - 10)")
    @RequestMapping(value = "/categories/{id}/news", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getShortNews(@PathVariable @Parameter(description = "id") final Long id,
                                          @RequestParam(required = false) @Parameter(description = "Номер страницы") final Integer page) {

        List<NewsShortDto> newsShort = NewsDtoConverter.fromNewsList(newsService.getNewsByCategoryId(id));
        JSONObject response = new JSONObject();
        response.put("code", 200);
        response.put("list", newsService.pageable(newsShort, page, 10));


        return ResponseEntity.ok(response.toMap());
    }

    @Operation(summary = "Implementation Notes", description = "Возвращает полную информацию о выбранной новости")
    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNewsDetails(@RequestParam @Parameter(description = "Id новости") final Long id) {

        JSONObject response = new JSONObject();
        response.put("code", 200);
        response.put("news", NewsDtoConverter.fromNewsByFull(newsService.getNewsById(id)));

        return ResponseEntity.ok(response.toMap());
    }
}