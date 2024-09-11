package com.project.uber.repositories;

import com.project.uber.entities.Driver;
import com.project.uber.entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.uber.entities.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride,Long> {

    Page<Ride> findByRider(Rider rider, Pageable pageable);

    Page<Ride> findByDriver(Driver driver, Pageable pageable);
}
