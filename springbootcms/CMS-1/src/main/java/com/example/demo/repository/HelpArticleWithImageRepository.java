package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.HelpArticleWithImage;

public interface HelpArticleWithImageRepository extends MongoRepository<HelpArticleWithImage, Long>{
	HelpArticleWithImage findTopByOrderByIdDesc();
}
