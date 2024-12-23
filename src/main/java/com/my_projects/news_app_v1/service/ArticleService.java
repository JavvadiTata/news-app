package com.my_projects.news_app_v1.service;

import com.my_projects.news_app_v1.entity.Article;
import com.my_projects.news_app_v1.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Page<Article> getAllArticles(Pageable pageable) {
        return articleRepository.findByPublishedDateBeforeOrderByPublishedDateDesc(LocalDateTime.now(), pageable);
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }
}