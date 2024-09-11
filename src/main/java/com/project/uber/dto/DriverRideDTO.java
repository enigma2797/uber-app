package com.project.uber.dto;

import com.project.uber.entities.enums.PaymentMethod;
import com.project.uber.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverRideDTO {



    private Long id;
    private PointDTO pickupLocation;

    private PointDTO dropOffLocation;


    private LocalDateTime createdTime;
    private RiderDTO rider;
    private DriverDTO driver;
    private PaymentMethod paymentMethod;
    private RideStatus rideStatus;

    private Double fare;
    private Double distance;
    private LocalDateTime startedAt;

    private LocalDateTime endedAt;
}
