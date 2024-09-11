package com.project.uber.services.impl;

import com.project.spring_mvc.exceptions.ResourceNotFoundException;
import com.project.uber.entities.Payment;
import com.project.uber.entities.Ride;
import com.project.uber.entities.enums.PaymentMethod;
import com.project.uber.entities.enums.PaymentStatus;
import com.project.uber.repositories.PaymentRepository;
import com.project.uber.services.PaymentService;
import com.project.uber.strategies.PaymentStrategy;
import com.project.uber.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentStrategyManager paymentStrategyManager;
    private final PaymentRepository paymentRepo;
    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = new Payment();
        payment.setRide(ride);
        payment.setAmount(ride.getFare());
        payment.setPaymentMethod(ride.getPaymentMethod());
        payment.setPaymentStatus(PaymentStatus.PENDING);
        return paymentRepo.save(payment);

    }

    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepo.findByRide(ride).orElseThrow(()-> new ResourceNotFoundException("Payment not found for ride id: "+ride.getId()));
     PaymentStrategy strategy = paymentStrategyManager.getPaymentStrategy(payment.getPaymentMethod());
     strategy.processPayment(payment);
    }

    @Override
    public Payment updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        return paymentRepo.save(payment);
    }
}
