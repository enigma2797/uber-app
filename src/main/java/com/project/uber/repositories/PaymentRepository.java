package com.project.uber.repositories;

import com.project.uber.entities.Payment;
import com.project.uber.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByRide(Ride ride);
}
