package com.project.uber.services.impl;

import com.project.spring_mvc.exceptions.ResourceNotFoundException;
import com.project.uber.dto.RideDTO;
import com.project.uber.dto.WalletDTO;
import com.project.uber.dto.WalletTransactionDTO;
import com.project.uber.entities.Ride;
import com.project.uber.entities.User;
import com.project.uber.entities.Wallet;
import com.project.uber.entities.WalletTransaction;
import com.project.uber.entities.enums.TransactionMethod;
import com.project.uber.entities.enums.TransactionType;
import com.project.uber.repositories.WalletRepository;
import com.project.uber.services.WalletService;
import com.project.uber.services.WalletTransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepo;
    private final ModelMapper mapper;
    private final WalletTransactionService walletTransactionService;
    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()+amount);
        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionMethod(transactionMethod)
                .transactionType(TransactionType.CREDIT)
                .amount(amount)
                .build();
        walletTransactionService.createNewWalletTransaction(walletTransaction);
        return walletRepo.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() - amount);
        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionMethod(transactionMethod)
                .transactionType(TransactionType.DEBIT)
                .amount(amount)
                .build();
        wallet.getWalletTransaction().add(walletTransaction);
        return walletRepo.save(wallet);
    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepo.findById(walletId).orElseThrow(()->new ResourceNotFoundException("wallet doesn't exist with id:"+walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(0.0);
        return walletRepo.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        Wallet wallet = walletRepo.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("wallet not found for user with id:"+user.getId()));
        return wallet;
    }
}
