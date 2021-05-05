package com.example.tutorial.semi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tutorial.semi.model.Tutorial;
import com.example.tutorial.semi.repo.TutorialRepository;

@Service
public class TutorialService {
	private static TutorialRepository tutorrepo;
	
	public TutorialService(TutorialRepository tutorrepo)
	{
		TutorialService.tutorrepo = tutorrepo;
	}

	//post or add a tutor
	public static Tutorial addTutor(Tutorial tutorial)
	{
		Tutorial _tutorial = tutorrepo.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(),false));
		return _tutorial;
		
	}
	//get all tutor
	public static List<Tutorial> getAllTutorials(){
		
		return tutorrepo.findAll();
		
	}
	
	//delete tutorial data
	public static void deleteTutorial(String id) {
		tutorrepo.deleteById(id);
	}
	
	public static Tutorial updateTutorial(String id, Tutorial tutor) {
		Optional tutorialData = tutorrepo.findById(id);
		
		Tutorial _tutorial = (Tutorial) tutorialData.get();
		_tutorial.setTitle(tutor.getTitle());
		_tutorial.setDescription(tutor.getDescription());
		_tutorial.setPublished(tutor.isPublished());
		return _tutorial;
		
	}

}
