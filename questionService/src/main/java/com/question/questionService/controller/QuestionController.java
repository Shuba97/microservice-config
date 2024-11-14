package com.question.questionService.controller;

import com.question.questionService.entity.Question;
import com.question.questionService.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public Question create(@RequestBody Question question){
        return questionService.create(question);
    }

    @GetMapping("/{id}")
    public Question get(@PathVariable Long id){
        return questionService.get(id);
    }

    @GetMapping("/AllQuestions")
    public List<Question> get(){
        return questionService.get();
    }

    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestions(@PathVariable Long quizId){
        return questionService.getQuestions(quizId);
    }


}
