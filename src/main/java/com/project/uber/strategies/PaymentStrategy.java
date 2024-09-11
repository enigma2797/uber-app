package com.project.uber.strategies;


import com.project.uber.entities.Payment;

public interface PaymentStrategy {

    static Double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);
}
