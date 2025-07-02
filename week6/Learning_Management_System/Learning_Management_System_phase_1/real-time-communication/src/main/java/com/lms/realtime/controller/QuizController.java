package com.lms.realtime.controller;

import com.lms.realtime.model.QuizMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class QuizController {
    @MessageMapping("/quiz.send")
    @SendTo("/topic/quiz")
    public QuizMessage sendQuizMessage(@Payload QuizMessage quizMessage) {
        quizMessage.setTimestamp(LocalDateTime.now());
        return quizMessage;
    }
} 