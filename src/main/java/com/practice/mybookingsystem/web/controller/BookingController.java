package com.practice.mybookingsystem.web.controller;

import com.practice.mybookingsystem.configuration.JwtUtil;
import com.practice.mybookingsystem.data.entity.Booking;
import com.practice.mybookingsystem.service.impl.BookingServiceImpl;
import com.practice.mybookingsystem.service.impl.UserServiceImpl;
import com.practice.mybookingsystem.web.dto.BookingRequestDto;
import com.practice.mybookingsystem.web.dto.BookingResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(
            @RequestHeader("Authorization") String token,
            @RequestBody BookingRequestDto bookingRequest) {

        Long userId = jwtUtil.extractUserId(token.substring(7));
        Booking booking = bookingService.createBooking(userId, bookingRequest);

        BookingResponseDto response = new BookingResponseDto(
                booking.getId(),
                booking.getEventId(),
                booking.getUserId(),
                booking.getBookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        );

        return ResponseEntity.status(201).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok().body("{\"message\": \"Booking canceled successfully\"}");
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getBookings(@RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);

        List<BookingResponseDto> bookingResponses = bookings.stream().map(booking -> new BookingResponseDto(
                booking.getId(),
                booking.getEvent().getId(),
                booking.getUser().getId(),
                booking.getBookingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        )).collect(Collectors.toList());

        return ResponseEntity.ok(bookingResponses);
    }

}
