package com.my_projects.news_app_v1.controllers;

import com.my_projects.news_app_v1.entity.Image;
import com.my_projects.news_app_v1.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/search")
    public List<Image> searchImagesByTags(@RequestParam List<String> tags) {
        return imageService.searchImagesByTags(tags);
    }

    @PostMapping
    public Image addImage(@RequestBody Image image) {
        return imageService.saveImage(image);
    }
}