package com.example.eventnotificationsystem.service;

import com.example.eventnotificationsystem.model.Event;
import com.example.eventnotificationsystem.observer.EventSubject;
import com.example.eventnotificationsystem.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EventServiceImplTest {
    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventSubject eventSubject;

    @InjectMocks
    private EventServiceImpl eventService;

    private Event testEvent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testEvent = new Event();
        testEvent.setId(1L);
        testEvent.setTitle("Test Event");
        testEvent.setDescription("Test Description");
        testEvent.setCreatedBy("testUser");
    }

    @Test
    void createEvent_Success() {
        when(eventRepository.save(any(Event.class))).thenReturn(testEvent);
        doNothing().when(eventSubject).setEvent(any(Event.class));

        Event result = eventService.createEvent(testEvent);

        assertNotNull(result);
        assertEquals(testEvent.getTitle(), result.getTitle());
        verify(eventRepository).save(any(Event.class));
        verify(eventSubject).setEvent(any(Event.class));
    }

    @Test
    void getEvent_Success() {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(testEvent));

        Event result = eventService.getEvent(1L);

        assertNotNull(result);
        assertEquals(testEvent.getId(), result.getId());
    }

    @Test
    void getEvent_NotFound() {
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> eventService.getEvent(1L));
    }

    @Test
    void getAllEvents_Success() {
        List<Event> events = Arrays.asList(testEvent);
        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = eventService.getAllEvents();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testEvent.getTitle(), result.get(0).getTitle());
    }

    @Test
    void updateEvent_Success() {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(testEvent));
        when(eventRepository.save(any(Event.class))).thenReturn(testEvent);
        doNothing().when(eventSubject).setEvent(any(Event.class));

        Event updatedEvent = new Event();
        updatedEvent.setTitle("Updated Title");
        updatedEvent.setDescription("Updated Description");
        updatedEvent.setCreatedBy("updatedUser");

        Event result = eventService.updateEvent(1L, updatedEvent);

        assertNotNull(result);
        assertEquals(updatedEvent.getTitle(), result.getTitle());
        verify(eventRepository).save(any(Event.class));
        verify(eventSubject).setEvent(any(Event.class));
    }

    @Test
    void deleteEvent_Success() {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(testEvent));
        doNothing().when(eventRepository).delete(any(Event.class));
        doNothing().when(eventSubject).setEvent(any(Event.class));

        eventService.deleteEvent(1L);

        verify(eventRepository).delete(any(Event.class));
        verify(eventSubject).setEvent(any(Event.class));
    }
} 