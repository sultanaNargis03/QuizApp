package com.quiz.quizapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.quizapp.model.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer>
{

	Optional<Quiz> findQuizById(Integer id);


}
