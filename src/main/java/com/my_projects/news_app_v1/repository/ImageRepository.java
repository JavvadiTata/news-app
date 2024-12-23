package com.my_projects.news_app_v1.repository;

import com.my_projects.news_app_v1.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByTagsIn(List<String> tags);
}