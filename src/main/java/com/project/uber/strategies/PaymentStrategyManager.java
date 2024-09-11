package com.project.uber.strategies;

import com.project.uber.entities.enums.PaymentMethod;
import com.project.uber.entities.enums.TransactionMethod;
import com.project.uber.entities.enums.TransactionType;
import com.project.uber.strategies.impl.CashPaymentStrategy;
import com.project.uber.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final CashPaymentStrategy cashPaymentStrategy;
    private final WalletPaymentStrategy walletPaymentStrategy;

    public PaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod)
    {
        if(paymentMethod.equals(PaymentMethod.CASH))
            return cashPaymentStrategy;
        else if(paymentMethod.equals(PaymentMethod.WALLET))
            return walletPaymentStrategy;
        else
            throw new RuntimeException("Not a valid payment method");
    }
}
