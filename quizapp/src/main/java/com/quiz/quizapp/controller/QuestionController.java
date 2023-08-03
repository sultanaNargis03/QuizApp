package com.quiz.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController 
{
	@Autowired
	QuestionService questionService;
	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions()
	{ 
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category)
	{ 
		return questionService.getQuestionsByCategory(category);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question)
	{ 
		return questionService.addQuestion(question);
	}
	@PutMapping("/update")
	public Question updateQuestion(@RequestBody Question question)
	{ 	
		return questionService.updateQuestion(question);
	}
	@DeleteMapping("/delete/{id}")
	public String deleteQuestion(@PathVariable int id)
	{ 
		return questionService.deleteQuestion(id);
	}
}
