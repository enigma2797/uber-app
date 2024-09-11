package com.project.uber.strategies.impl;

import com.project.uber.entities.Driver;
import com.project.uber.entities.Payment;
import com.project.uber.entities.Rider;
import com.project.uber.entities.enums.PaymentStatus;
import com.project.uber.entities.enums.TransactionMethod;
import com.project.uber.repositories.PaymentRepository;
import com.project.uber.services.PaymentService;
import com.project.uber.services.WalletService;
import com.project.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepo;
    @Override
    public void processPayment(Payment payment) {

        Rider rider = payment.getRide().getRider();
        Driver driver = payment.getRide().getDriver();
        double driverPayment = payment.getAmount() - PLATFORM_COMMISSION* payment.getAmount();
        walletService.deductMoneyFromWallet(rider.getUser(),payment.getAmount(),null,payment.getRide(), TransactionMethod.RIDE );
        walletService.addMoneyToWallet(driver.getUser(),driverPayment,null,payment.getRide(),TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentRepo.save(payment);

    }
}
