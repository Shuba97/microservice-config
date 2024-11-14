package com.quiz.quizService.service;

import com.quiz.quizService.entites.Quiz;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface QuizService {
    Quiz add(Quiz quiz);
    List<Quiz> get();
    // completeableFuture for only time limiter(return completion stage type return type)
    CompletableFuture<Quiz> get(Long id);
    // for rate limiter
    // Quiz get(Long id);
}
