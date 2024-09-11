package com.project.uber.services;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.RiderDTO;
import com.project.uber.entities.Driver;
import com.project.uber.entities.Ride;
import com.project.uber.entities.Rider;

public interface RatingService {

    DriverDTO rateDriver(Ride ride, Double rating);
    RiderDTO rateRider(Ride ride, Double rating);
    void createNewRating(Ride ride);
}
