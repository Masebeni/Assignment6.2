package com.mobile.factories.transaction;


import com.mobile.domain.transaction.Payment;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class PaymentFactory {
    public static Payment getPayment(long identity, String from, String to, float money){
        return new Payment.Builder()
                .id(identity)
                .fromAccount(from)
                .toAccount(to)
                .amount(money)
                .build();
    }
    public static String getPayment(String transaction) {
        return "Payment";
    }
}
