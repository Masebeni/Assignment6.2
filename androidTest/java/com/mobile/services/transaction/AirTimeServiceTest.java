package com.mobile.services.transaction;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.mobile.factories.transaction.AirTimeFactory;

public class AirTimeServiceTest extends Service implements AirtimeService {
    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }
    public class RetrieveAccountInfoLocalBinder extends Binder {
        public AirTimeServiceTest getService()
        {
            return  AirTimeServiceTest.this;
        }
    }

    public AirTimeServiceTest() {
    }

    @Override
    public String getTransaction(String transaction) {
        return AirTimeFactory.getAirTime(transaction);
    }
}
