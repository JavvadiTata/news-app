package com.my_projects.news_app_v1.controllers;

import com.my_projects.news_app_v1.entity.Article;
import com.my_projects.news_app_v1.entity.Image;
import com.my_projects.news_app_v1.service.ArticleService;
import com.my_projects.news_app_v1.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private S3Service s3Service;

    @GetMapping
    public Page<Article> getAllArticles(Pageable pageable) {
        return articleService.getAllArticles(pageable);
    }

    @PostMapping
    public Article postArticle(@RequestPart("article") Article article,
                               @RequestPart("image") MultipartFile imageFile,
                               @RequestPart("tags") List<String> tags) throws IOException {
        if (!imageFile.isEmpty()) {
            String imageUrl = s3Service.uploadFile(imageFile);
            Image image = new Image();
            image.setUrl(imageUrl);
            image.setTags(tags);
            article.setImage(image);
        }
        return articleService.saveArticle(article);
    }
}