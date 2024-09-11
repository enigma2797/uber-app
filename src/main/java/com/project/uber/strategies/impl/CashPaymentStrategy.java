package com.project.uber.strategies.impl;

import com.project.uber.entities.Driver;
import com.project.uber.entities.Payment;
import com.project.uber.entities.Wallet;
import com.project.uber.entities.enums.PaymentStatus;
import com.project.uber.entities.enums.TransactionMethod;
import com.project.uber.repositories.PaymentRepository;
import com.project.uber.repositories.WalletRepository;
import com.project.uber.services.PaymentService;
import com.project.uber.services.WalletService;
import com.project.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepo;

    @Override
    public void processPayment(Payment payment) {
     Driver driver = payment.getRide().getDriver();
     double amount = PLATFORM_COMMISSION * payment.getAmount();
     walletService.deductMoneyFromWallet(driver.getUser(),amount,null,payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentRepo.save(payment);
    }
}
