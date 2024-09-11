package com.project.uber.dto;

import com.project.uber.entities.Ride;
import com.project.uber.entities.Wallet;
import com.project.uber.entities.enums.TransactionMethod;
import com.project.uber.entities.enums.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDTO {

    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
    private RideDTO ride;

    private String transactionId;
    private LocalDateTime timestamp;
    private WalletDTO wallet;
}
