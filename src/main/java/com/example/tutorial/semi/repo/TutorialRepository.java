package com.example.tutorial.semi.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.tutorial.semi.model.Tutorial;

public interface TutorialRepository extends MongoRepository<Tutorial, String>{
	List<Tutorial> findByTitleContaining(String title);
	List<Tutorial> findByPublished(boolean published);
}
