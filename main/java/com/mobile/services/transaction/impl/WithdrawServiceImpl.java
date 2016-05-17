package com.mobile.services.transaction.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.mobile.factories.transaction.WithdrawFactory;
import com.mobile.services.transaction.WithdrawService;

public class WithdrawServiceImpl extends Service implements WithdrawService {
    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }
    public class RetrieveAccountInfoLocalBinder extends Binder {
        public WithdrawServiceImpl getService()
        {
            return  WithdrawServiceImpl.this;
        }
    }

    public WithdrawServiceImpl() {
    }

    @Override
    public String getTransaction(String transaction) {
        return WithdrawFactory.getWithdrawal(transaction);
    }
}
