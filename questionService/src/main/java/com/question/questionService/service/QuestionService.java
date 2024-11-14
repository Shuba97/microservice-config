package com.question.questionService.service;

import com.question.questionService.entity.Question;

import java.util.List;

public interface QuestionService {
    Question create(Question question);

    Question get(Long id);

    List<Question> get();

    List<Question> getQuestions(Long quizId);
}
