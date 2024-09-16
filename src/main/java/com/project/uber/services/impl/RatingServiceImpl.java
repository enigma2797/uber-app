package com.project.uber.services.impl;

import com.project.uber.exceptions.ResourceNotFoundException;
import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.RiderDTO;
import com.project.uber.entities.Driver;
import com.project.uber.entities.Rating;
import com.project.uber.entities.Ride;
import com.project.uber.entities.Rider;
import com.project.uber.repositories.DriverRepository;
import com.project.uber.repositories.RatingRepository;
import com.project.uber.repositories.RiderRepository;
import com.project.uber.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepo;
    private final DriverRepository driverRepo;
    private final RiderRepository riderRepo;
    private final ModelMapper mapper;
    @Override
    public DriverDTO rateDriver(Ride ride, Double rating) {

        Rating ratingObj = ratingRepo.findByRide(ride).orElseThrow(()-> new ResourceNotFoundException("Rating object not found "));
        ratingObj.setDriverRating(rating);
        ratingRepo.save(ratingObj);
        Driver driver = ride.getDriver();
        Double newRating = ratingRepo.findByDriver(driver)
                .stream()
                .mapToDouble(Rating::getDriverRating).average().orElse(0.0);
        driver.setRating(newRating);
        Driver savedDriver = driverRepo.save(driver);
        return mapper.map(savedDriver, DriverDTO.class);
    }

    @Override
    public RiderDTO rateRider(Ride ride, Double rating) {
        Rating ratingObj = ratingRepo.findByRide(ride).orElseThrow(()-> new ResourceNotFoundException("Rating object not found "));
        ratingObj.setRiderRating(rating);
        ratingRepo.save(ratingObj);
        Rider rider = ride.getRider();
        Double newRating = ratingRepo.findByRider(ride.getRider())
                .stream()
                .mapToDouble(Rating::getRiderRating).average().orElse(0.0);
        rider.setRating(newRating);
        Rider savedRider = riderRepo.save(rider);

        return mapper.map(savedRider,RiderDTO.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = Rating.builder().driver(ride.getDriver())
                .rider(ride.getRider())
                .driverRating(0.0)
                .riderRating(0.0)
                .build();
        ratingRepo.save(rating);
    }
}
