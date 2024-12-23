package com.my_projects.news_app_v1.repository;

import com.my_projects.news_app_v1.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByPublishedDateBeforeOrderByPublishedDateDesc(LocalDateTime now, Pageable pageable);
}