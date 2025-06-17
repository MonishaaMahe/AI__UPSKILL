package com.example.eventnotificationsystem.repository;

import com.example.eventnotificationsystem.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
} 