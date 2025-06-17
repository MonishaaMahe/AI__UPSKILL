package com.example.eventnotificationsystem.service;

import com.example.eventnotificationsystem.model.Event;
import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    Event getEvent(Long id);
    List<Event> getAllEvents();
    Event updateEvent(Long id, Event event);
    void deleteEvent(Long id);
} 