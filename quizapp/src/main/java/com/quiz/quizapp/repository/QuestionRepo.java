package com.quiz.quizapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quiz.quizapp.model.Question;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface QuestionRepo extends JpaRepository<Question,Integer>{

	List<Question> findByCategory(String category);

	void deleteQuestionById(int id);
	@Query(value="SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :noQ",nativeQuery=true)
	List<Question> findRandomQuestionsByCategory(String category, int noQ);

	

}
