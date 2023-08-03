package com.quiz.questionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quiz.questionservice.model.Question;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface QuestionRepo extends JpaRepository<Question,Integer>{

	List<Question> findByCategory(String category);

	void deleteQuestionById(int id);
	@Query(value="SELECT q.id FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :noQ",nativeQuery=true)
	List<Integer> findRandomQuestionsByCategory(String category, Integer noQ);

	

}
