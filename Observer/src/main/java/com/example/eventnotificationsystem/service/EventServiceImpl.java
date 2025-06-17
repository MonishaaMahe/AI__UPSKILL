package com.example.eventnotificationsystem.service;

import com.example.eventnotificationsystem.model.Event;
import com.example.eventnotificationsystem.observer.EventSubject;
import com.example.eventnotificationsystem.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventSubject eventSubject;

    @Override
    public Event createEvent(Event event) {
        Event savedEvent = eventRepository.save(event);
        eventSubject.setEvent(savedEvent);
        return savedEvent;
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        Event existingEvent = getEvent(id);
        existingEvent.setTitle(event.getTitle());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setCreatedBy(event.getCreatedBy());
        Event updatedEvent = eventRepository.save(existingEvent);
        eventSubject.setEvent(updatedEvent);
        return updatedEvent;
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = getEvent(id);
        eventRepository.delete(event);
        eventSubject.setEvent(event);
    }
} 