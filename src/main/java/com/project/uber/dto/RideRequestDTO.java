package com.project.uber.dto;

import java.time.LocalDateTime;

import org.locationtech.jts.geom.Point;

import com.project.uber.entities.enums.PaymentMethod;
import com.project.uber.entities.enums.RideRequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO {
	
	private Long id;
	private PointDTO pickupLocation;
	private PointDTO dropOffLocation;
	private LocalDateTime requestedTime;
	private RiderDTO rider;
	private RideRequestStatus rideStatus;
    private PaymentMethod paymentMethod;
    private Double fare;
	private Double distance;
}
