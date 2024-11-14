package com.quiz.quizService.service;

import com.quiz.quizService.entites.Quiz;
import com.quiz.quizService.repositories.QuizRepository;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuestionClient questionClient;

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override

    public List<Quiz> get() {
        List<Quiz> quizzes = quizRepository.findAll();
        List<Quiz> newQuizList = quizzes.stream().map(quiz -> {
            quiz.setQuestions(questionClient.getQuestionsOfQuiz(quiz.getId()));
            return quiz;
        }).toList();
        return newQuizList;
    }
    int retryCount=1;
    @Override
   // @CircuitBreaker(name = "question-service", fallbackMethod = "quizFallback")
   // @Retry(name="question-service",fallbackMethod = "quizFallback")
   // @RateLimiter(name="quiz-rate-limiter",fallbackMethod = "quizFallback")
//    public Quiz get(Long id) {
//       log.info("Retry count:{}", retryCount);
//        retryCount++;
//        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
//        quiz.setQuestions(questionClient.getQuestionsOfQuiz(quiz.getId()));
//
//        return quiz;
//    }

    //fallback for ratelimiter method
//    public Quiz quizFallback(Long id, Throwable e) {
//        log.info("Quiz service is down: {}", e.getMessage());
//        Quiz dummyQuiz = new Quiz();
//        dummyQuiz.setId(-1L);
//        dummyQuiz.setTitle("Default Quiz");
//        dummyQuiz.setQuestions(Collections.emptyList());
//        return dummyQuiz;
//    }
// this will auto hit api to retry service which is down
//    @Scheduled(fixedDelay = 10000) // Runs every 10 seconds
//    public void scheduledRetryCheck() {
//        try {
//            get(1L); // Test with a sample ID
//        } catch (Exception e) {
//            log.info("Scheduled check: Service is still unavailable");
//        }
//    }


    @TimeLimiter(name = "quiz-time-limiter", fallbackMethod = "quizFallback")

    public CompletableFuture<Quiz> get(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Quiz quiz = quizRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Quiz not found"));
            quiz.setQuestions(questionClient.getQuestionsOfQuiz(quiz.getId()));
            return quiz;
        });
    }

    // Fallback method for time limiter
    public CompletableFuture<Quiz> quizFallback(Throwable e) {
        log.info("Quiz service timed out: {}", e.getMessage());
        Quiz dummyQuiz = new Quiz();
        dummyQuiz.setId(-1L);
        dummyQuiz.setTitle("Default Quiz");
        dummyQuiz.setQuestions(Collections.emptyList());
        return CompletableFuture.completedFuture(dummyQuiz);
    }


}
