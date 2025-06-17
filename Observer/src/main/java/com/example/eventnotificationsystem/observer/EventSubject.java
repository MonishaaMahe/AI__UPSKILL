package com.example.eventnotificationsystem.observer;

import com.example.eventnotificationsystem.model.Event;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private Event event;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    public void setEvent(Event event) {
        this.event = event;
        notifyObservers();
    }
} 