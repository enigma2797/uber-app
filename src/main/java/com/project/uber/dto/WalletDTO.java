package com.project.uber.dto;

import com.project.uber.entities.User;
import com.project.uber.entities.WalletTransaction;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class WalletDTO {

    private Long id;
    private UserDTO user;

    private Double balance;
    private List<WalletTransactionDTO> walletTransaction;
}
