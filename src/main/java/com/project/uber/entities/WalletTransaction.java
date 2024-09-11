package com.project.uber.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import com.project.uber.entities.enums.TransactionMethod;
import com.project.uber.entities.enums.TransactionType;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(indexes = {
		@Index(name="idx_wallet_transaction_wallet",columnList = "wallet"),
		@Index(name="idx_wallet_transaction_ride",columnList = "ride")
})
public class WalletTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double amount;
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@Enumerated(EnumType.STRING)
	private TransactionMethod transactionMethod;
	
	@ManyToOne
	private Ride ride;
	
	private String transactionId;
	
	@CreationTimestamp
	private LocalDateTime timestamp;
	
	@ManyToOne
	private Wallet wallet;
}

