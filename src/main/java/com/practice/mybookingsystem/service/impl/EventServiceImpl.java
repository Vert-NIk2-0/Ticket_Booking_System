package com.practice.mybookingsystem.service.impl;

import com.practice.mybookingsystem.data.EventRepository;
import com.practice.mybookingsystem.data.entity.Event;
import com.practice.mybookingsystem.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getFilteredAndSortedEvents(String date, String location, String sort) {
        List<Event> events = eventRepository.findAll();

        if (date != null) {
            events = events.stream()
                    .filter(event -> event.getDate().equals(date))
                    .collect(Collectors.toList());
        }

        if (location != null) {
            events = events.stream()
                    .filter(event -> event.getLocation().equalsIgnoreCase(location))
                    .collect(Collectors.toList());
        }

        if ("desc".equalsIgnoreCase(sort)) {
            events.sort((e1, e2) -> e2.getDate().compareTo(e1.getDate()));
        } else {
            events.sort((e1, e2) -> e1.getDate().compareTo(e2.getDate()));
        }

        return events;
    }

}
