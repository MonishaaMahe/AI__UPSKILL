package com.lms.realtime.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizMessage {
    private String quizId;
    private String sender;
    private String content;
    private LocalDateTime timestamp;
    private MessageType type;

    public enum MessageType {
        QUESTION, ANSWER, RESULT
    }
} 