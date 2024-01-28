package com.BusBooking.repository;

import com.BusBooking.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger , Long> {
}
