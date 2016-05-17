package com.mobile.services.transaction;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.mobile.factories.transaction.WithdrawFactory;

public class WithdrawServiceTest extends Service implements WithdrawService {
    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }
    public class RetrieveAccountInfoLocalBinder extends Binder {
        public WithdrawServiceTest getService()
        {
            return  WithdrawServiceTest.this;
        }
    }

    public WithdrawServiceTest() {
    }

    @Override
    public String getTransaction(String transaction) {
        return WithdrawFactory.getWithdrawal(transaction);
    }
}
