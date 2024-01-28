package com.BusBooking.repository;

import com.BusBooking.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule , Long> {
}
