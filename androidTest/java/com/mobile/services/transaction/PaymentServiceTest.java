package com.mobile.services.transaction;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.mobile.factories.transaction.PaymentFactory;

public class PaymentServiceTest extends Service implements PaymentService {
    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }
    public class RetrieveAccountInfoLocalBinder extends Binder {
        public PaymentServiceTest getService()
        {
            return  PaymentServiceTest.this;
        }
    }

    public PaymentServiceTest() {
    }

    @Override
    public String getTransaction(String transaction) {
        return PaymentFactory.getPayment(transaction);
    }
}
