package com.project.uber.services;

import com.project.uber.entities.Ride;
import com.project.uber.entities.User;
import com.project.uber.entities.Wallet;
import com.project.uber.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
    void withdrawAllMyMoneyFromWallet();
    Wallet findWalletById(Long walletId);
    Wallet createNewWallet(User user);
    Wallet findByUser(User user);
}
