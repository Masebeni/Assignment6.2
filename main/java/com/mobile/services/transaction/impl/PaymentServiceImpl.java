package com.mobile.services.transaction.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.mobile.factories.transaction.PaymentFactory;
import com.mobile.services.transaction.PaymentService;

public class PaymentServiceImpl extends Service implements PaymentService {
    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }
    public class RetrieveAccountInfoLocalBinder extends Binder {
        public PaymentServiceImpl getService()
        {
            return  PaymentServiceImpl.this;
        }
    }

    public PaymentServiceImpl() {
    }

    @Override
    public String getTransaction(String transaction) {
        return PaymentFactory.getPayment(transaction);
    }
}
