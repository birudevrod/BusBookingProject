package com.BusBooking.repository;

import com.BusBooking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking , Long> {
}
