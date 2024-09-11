package com.project.uber.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import com.project.uber.entities.enums.PaymentMethod;
import com.project.uber.entities.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	@OneToOne(fetch = FetchType.LAZY)
	private Ride ride;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	private Double amount;
	@CreationTimestamp
	private LocalDateTime paymentTime;
}
