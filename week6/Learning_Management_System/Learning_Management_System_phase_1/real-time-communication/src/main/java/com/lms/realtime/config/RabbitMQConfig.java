package com.lms.realtime.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String CHAT_EXCHANGE = "chat-exchange";
    public static final String QUIZ_EXCHANGE = "quiz-exchange";
    public static final String ANNOUNCEMENT_EXCHANGE = "announcement-exchange";
    public static final String NOTIFICATION_EXCHANGE = "notification-exchange";

    public static final String CHAT_QUEUE = "chat-queue";
    public static final String QUIZ_QUEUE = "quiz-queue";
    public static final String ANNOUNCEMENT_QUEUE = "announcement-queue";
    public static final String NOTIFICATION_QUEUE = "notification-queue";

    @Bean
    public TopicExchange chatExchange() {
        return new TopicExchange(CHAT_EXCHANGE);
    }
    @Bean
    public TopicExchange quizExchange() {
        return new TopicExchange(QUIZ_EXCHANGE);
    }
    @Bean
    public TopicExchange announcementExchange() {
        return new TopicExchange(ANNOUNCEMENT_EXCHANGE);
    }
    @Bean
    public TopicExchange notificationExchange() {
        return new TopicExchange(NOTIFICATION_EXCHANGE);
    }

    @Bean
    public Queue chatQueue() {
        return new Queue(CHAT_QUEUE);
    }
    @Bean
    public Queue quizQueue() {
        return new Queue(QUIZ_QUEUE);
    }
    @Bean
    public Queue announcementQueue() {
        return new Queue(ANNOUNCEMENT_QUEUE);
    }
    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE);
    }

    @Bean
    public Binding chatBinding() {
        return BindingBuilder.bind(chatQueue()).to(chatExchange()).with("chat.#");
    }
    @Bean
    public Binding quizBinding() {
        return BindingBuilder.bind(quizQueue()).to(quizExchange()).with("quiz.#");
    }
    @Bean
    public Binding announcementBinding() {
        return BindingBuilder.bind(announcementQueue()).to(announcementExchange()).with("announcement.#");
    }
    @Bean
    public Binding notificationBinding() {
        return BindingBuilder.bind(notificationQueue()).to(notificationExchange()).with("notification.#");
    }
} 