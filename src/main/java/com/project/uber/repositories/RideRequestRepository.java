package com.project.uber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.uber.entities.RideRequest;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {

}
