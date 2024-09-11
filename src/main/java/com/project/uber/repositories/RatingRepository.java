package com.project.uber.repositories;

import com.project.uber.entities.Driver;
import com.project.uber.entities.Rating;
import com.project.uber.entities.Ride;
import com.project.uber.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating,Long> {

    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}
