package com.quiz.quizservice.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quizservice.feign.QuizInterface;
import com.quiz.quizservice.model.QuestionWrapper;
import com.quiz.quizservice.model.Quiz;
import com.quiz.quizservice.model.Response;
import com.quiz.quizservice.repository.QuizRepo;

import jakarta.transaction.Transactional;


@Transactional
@Service
public class QuizService
{
	@Autowired
	QuizRepo quizRepo;
	@Autowired
	QuizInterface quizInterface;
	

	public ResponseEntity<String> createQuiz(String category, String title, int noQ) 
	{
		List<Integer> questions=quizInterface.getQuestionsForQuiz(category,noQ).getBody();
		
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		quizRepo.save(quiz);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) 
	{
		Quiz quiz=quizRepo.findQuizById(id).get();
		List<Integer> questionIds=quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questions=quizInterface.getQuestionsFromId(questionIds);
		return questions;
	}

	public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses)
	{
		ResponseEntity<Integer> score=quizInterface.getScore(responses);
		return score;
	}
	
}
