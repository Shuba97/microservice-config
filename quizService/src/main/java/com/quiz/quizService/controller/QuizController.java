package com.quiz.quizService.controller;

import com.quiz.quizService.entites.Quiz;
import com.quiz.quizService.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
@Slf4j

public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public Quiz create(@RequestBody Quiz quiz) {
        return quizService.add(quiz);
    }

    @GetMapping("/{id}")
    public CompletableFuture<Quiz> get(@PathVariable Long id){
        return quizService.get(id);
    }


    @GetMapping
    public List<Quiz> get(){
        return quizService.get();
    }


}
