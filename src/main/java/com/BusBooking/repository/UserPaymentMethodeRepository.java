package com.BusBooking.repository;

import com.BusBooking.entities.UserPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentMethodeRepository extends JpaRepository<UserPaymentMethod , Long> {
}
