package com.lms.realtime.controller;

import com.lms.realtime.model.NotificationMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class NotificationController {
    @MessageMapping("/notification.send")
    @SendTo("/topic/notifications")
    public NotificationMessage sendNotification(@Payload NotificationMessage notificationMessage) {
        notificationMessage.setTimestamp(LocalDateTime.now());
        return notificationMessage;
    }
} 