package com.practice.mybookingsystem.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingResponseDto {
    private Long id;
    private Long eventId;
    private Long userId;
    private String bookingDate;

}
