package com.example.tutorial.semi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tutorial.semi.model.Tutorial;
import com.example.tutorial.semi.repo.TutorialRepository;
import com.example.tutorial.semi.service.TutorialService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

	 @Autowired
	  TutorialRepository tutorialRepository;
	 @GetMapping("/tutorials")
	  public ResponseEntity<List<Tutorial>> getAllTutorials() {
		
			    return ResponseEntity.ok(TutorialService.getAllTutorials());
			  
	  }

	  @GetMapping("/tutorials/{id}")
	  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") String id) {
		  Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		  if (tutorialData.isPresent()) {
		    return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	  }

	  @PostMapping("/tutorials")
	  public Tutorial createTutorial(@RequestBody Tutorial tutorial) {
	
		    return TutorialService.addTutor(tutorial);
		 
	  }

	  @PutMapping("/tutorials/{id}")
	  public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") String id, @RequestBody Tutorial tutorial) {
		  Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		  if (tutorialData.isPresent()) {
			  
		    return new ResponseEntity<>(tutorialRepository.save(TutorialService.updateTutorial(id, tutorial)), HttpStatus.OK);
		  } else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
	  }

	  @DeleteMapping("/tutorials/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
		 
			    tutorialRepository.deleteById(id);
			   
			    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
	    
	  }

	  @DeleteMapping("/tutorials")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() {
		  try {
			    tutorialRepository.deleteAll();
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  } catch (Exception e) {
			    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			  }
	    
	  }

}
