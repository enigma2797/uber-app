package com.project.uber.dto;

import java.time.LocalDateTime;


import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import com.project.uber.entities.Driver;
import com.project.uber.entities.Rider;
import com.project.uber.entities.enums.PaymentMethod;
import com.project.uber.entities.enums.RideStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDTO extends DriverRideDTO{
	

	private String otp;

}
