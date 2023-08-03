package com.quiz.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.QuestionWrapper;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.model.Response;
import com.quiz.quizapp.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController 
{
	@Autowired
	QuizService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int noQ,@RequestParam String title)
	{
		return service.createQuiz(category,noQ,title);
	}
	
	@GetMapping("/getQuiz/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
	{
		return service.getQuizQuestions(id);
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses)
	{
		return service.calculateScore(id,responses);
	}
	
}
