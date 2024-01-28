package com.BusBooking.repository;

import com.BusBooking.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer , Long> {
}
