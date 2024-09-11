package com.project.uber.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import com.project.uber.dto.RiderDTO;
import com.project.uber.entities.enums.PaymentMethod;
import com.project.uber.entities.enums.RideRequestStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
		@Index(name="idx_rider",columnList = "rider_id")
})
public class RideRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "Geometry(Point,4326)")
	private Point pickupLocation;
	@Column(columnDefinition = "Geometry(Point,4326)")
	private Point dropOffLocation;
	
	@CreationTimestamp
	private LocalDateTime requestedTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Rider rider;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@Enumerated(EnumType.STRING)
	private RideRequestStatus rideStatus;
	
	private Double fare;
	private Double distance;

}
