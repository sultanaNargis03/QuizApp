package com.quiz.quizservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.quizservice.model.Quiz;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer>
{

	Optional<Quiz> findQuizById(Integer id);


}
