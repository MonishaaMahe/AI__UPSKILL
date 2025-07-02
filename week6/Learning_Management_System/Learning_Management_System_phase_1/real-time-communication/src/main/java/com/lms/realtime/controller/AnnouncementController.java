package com.lms.realtime.controller;

import com.lms.realtime.model.AnnouncementMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class AnnouncementController {
    @MessageMapping("/announcement.send")
    @SendTo("/topic/announcements")
    public AnnouncementMessage sendAnnouncement(@Payload AnnouncementMessage announcementMessage) {
        announcementMessage.setTimestamp(LocalDateTime.now());
        return announcementMessage;
    }
} 