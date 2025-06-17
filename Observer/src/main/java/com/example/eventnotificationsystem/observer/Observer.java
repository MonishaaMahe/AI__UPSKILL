package com.example.eventnotificationsystem.observer;

import com.example.eventnotificationsystem.model.Event;

public interface Observer {
    void update(Event event);
} 