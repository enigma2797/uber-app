package com.project.uber.services.impl;

import com.project.uber.dto.WalletTransactionDTO;
import com.project.uber.entities.WalletTransaction;
import com.project.uber.repositories.WalletTransactionRepository;
import com.project.uber.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {
    private final WalletTransactionRepository walletTransactionRepo;
    private final ModelMapper mapper;
    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {

        walletTransactionRepo.save(walletTransaction);

    }
}
