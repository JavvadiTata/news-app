package com.my_projects.news_app_v1.service;

import com.my_projects.news_app_v1.entity.Image;
import com.my_projects.news_app_v1.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<Image> searchImagesByTags(List<String> tags) {
        return imageRepository.findByTagsIn(tags);
    }

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }
}