package com.mobile.services.transaction.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.mobile.factories.transaction.AirTimeFactory;
import com.mobile.services.transaction.AirtimeService;

public class AirTimeServiceImpl extends Service implements AirtimeService {
    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }
    public class RetrieveAccountInfoLocalBinder extends Binder {
        public AirTimeServiceImpl getService()
        {
            return  AirTimeServiceImpl.this;
        }
    }

    public AirTimeServiceImpl() {
    }

    @Override
    public String getTransaction(String transaction) {
        return AirTimeFactory.getAirTime(transaction);
    }
}
