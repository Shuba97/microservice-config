package com.quiz.quizService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
//@EnableScheduling
@EnableDiscoveryClient
public class QuizServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(QuizServiceApplication.class, args);
	}

}
