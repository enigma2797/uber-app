package com.project.uber.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import com.project.uber.entities.enums.PaymentMethod;
import com.project.uber.entities.enums.RideStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = {
		@Index(name="rider_ix",columnList = "rider_id"),
		@Index(name="driver_ix",columnList = "driver_id")
})
public class Ride {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "Geometry(Point,4326)")
	private Point pickupLocation;
	@Column(columnDefinition = "Geometry(Point,4326)")
	private Point dropOffLocation;
	
	@CreationTimestamp
	private LocalDateTime createdTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Rider rider;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Driver driver;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@Enumerated(EnumType.STRING)
	private RideStatus rideStatus;
	
	private Double fare;
	private Double distance;
	private LocalDateTime startedAt;
	
	private LocalDateTime endedAt;
	private String otp;
}
