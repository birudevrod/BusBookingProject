package com.BusBooking.repository;

import com.BusBooking.entities.UserOffer;
import com.BusBooking.util.UserOfferId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOfferRepository extends JpaRepository<UserOffer , UserOfferId> {
}
