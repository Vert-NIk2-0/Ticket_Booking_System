package com.practice.mybookingsystem.service;

import com.practice.mybookingsystem.data.entity.Event;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    List<Event> getFilteredAndSortedEvents(String date, String location, String sort);
}
