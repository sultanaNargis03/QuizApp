package com.quiz.quizservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto 
{
	private String categoryName;
	private String title;
	private Integer numQuestions;
	
}
