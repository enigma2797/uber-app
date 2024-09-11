package com.project.uber.services;

import com.project.uber.entities.Payment;
import com.project.uber.entities.Ride;
import com.project.uber.entities.enums.PaymentStatus;

public interface PaymentService {

    Payment createNewPayment(Ride ride);
    void processPayment(Ride ride);
    Payment updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
