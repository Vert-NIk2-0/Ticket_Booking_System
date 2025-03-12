package com.practice.mybookingsystem.web.controller;

import com.practice.mybookingsystem.data.entity.Event;
import com.practice.mybookingsystem.service.impl.EventServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventServiceImpl eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getEvents(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String location,
            @RequestParam(required = false, defaultValue = "asc") String sort) {

        List<Event> events = eventService.getFilteredAndSortedEvents(date, location, sort);
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.status(201).body(createdEvent);
    }
}
