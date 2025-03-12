package com.practice.mybookingsystem.service;

import com.practice.mybookingsystem.data.entity.Booking;
import com.practice.mybookingsystem.web.dto.BookingRequestDto;

import java.util.List;

public interface BookingService {
    Booking createBooking(Long userId, BookingRequestDto bookingRequest);
    void cancelBooking(Long bookingId);
    List<Booking> getBookingsByUserId(Long userId);
}
