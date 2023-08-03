package com.quiz.quizapp.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.quizapp.model.Question;
import com.quiz.quizapp.model.QuestionWrapper;
import com.quiz.quizapp.model.Quiz;
import com.quiz.quizapp.model.Response;
import com.quiz.quizapp.repository.QuestionRepo;
import com.quiz.quizapp.repository.QuizRepo;

@Service
public class QuizService
{
	@Autowired
	QuizRepo quizRepo;
	
	@Autowired
	QuestionRepo questionRepo;

	public ResponseEntity<String> createQuiz(String category, int noQ, String title) {
		
		List<Question> questions=questionRepo.findRandomQuestionsByCategory(category,noQ);
		
		Quiz quiz=new Quiz();
		quiz.setTitle(title); 
		quiz.setQuestions(questions);
		
		quizRepo.save(quiz);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) 
	{
		Optional<Quiz> quiz=quizRepo.findQuizById(id);
		List<Question> questionsFromDb=quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser=new ArrayList<>();
		for(Question q:questionsFromDb) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionsForUser.add(qw);
		}	
		return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {
		
		Optional<Quiz> quiz=quizRepo.findQuizById(id);
		List<Question> questions=quiz.get().getQuestions();
		int i=0;
		int right=0;
		for(Response response:responses)
		{
			if(response.getResponse().equals(questions.get(i).getRightAnswer()))
			
				right++;
			i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
	
}
