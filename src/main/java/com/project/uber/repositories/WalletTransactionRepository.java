package com.project.uber.repositories;

import com.project.uber.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction,Long> {
}
