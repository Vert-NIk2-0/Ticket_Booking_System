package com.practice.mybookingsystem.service.impl;

import com.practice.mybookingsystem.data.BookingRepository;
import com.practice.mybookingsystem.data.EventRepository;
import com.practice.mybookingsystem.data.UserRepository;
import com.practice.mybookingsystem.data.entity.Booking;
import com.practice.mybookingsystem.data.entity.Event;
import com.practice.mybookingsystem.data.entity.User;
import com.practice.mybookingsystem.service.BookingService;
import com.practice.mybookingsystem.web.dto.BookingRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Long userId, BookingRequestDto bookingRequest) {
        Long eventId = bookingRequest.getEventId();

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        if (event.getAvailableTickets() <= 0) {
            throw new IllegalArgumentException("No tickets available for this event");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        event.setAvailableTickets(event.getAvailableTickets() - 1);
        eventRepository.save(event);

        Booking booking = new Booking();
        booking.setEvent(event);
        booking.setUser(user);
        booking.setBookingDate(LocalDateTime.now());

        return bookingRepository.save(booking);

    }

    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        Event event = booking.getEvent();
        event.setAvailableTickets(event.getAvailableTickets() + 1);
        eventRepository.save(event);

        bookingRepository.delete(booking);
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return bookingRepository.findByUser(user);
    }
}
