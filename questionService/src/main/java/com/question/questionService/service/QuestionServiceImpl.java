package com.question.questionService.service;

import com.question.questionService.entity.Question;
import com.question.questionService.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    @Override
    public Question create(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question get(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(()->new RuntimeException("no question found"));
    }

    @Override
    public List<Question> get() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestions(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }
}
