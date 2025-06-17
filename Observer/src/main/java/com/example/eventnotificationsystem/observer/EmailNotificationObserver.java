package com.example.eventnotificationsystem.observer;

import com.example.eventnotificationsystem.model.Event;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailNotificationObserver implements Observer {
    @Override
    public void update(Event event) {
        // In a real application, this would send an email
        log.info("Sending email notification for event: {}", event.getTitle());
        log.info("Event details: {}", event.getDescription());
    }
} 