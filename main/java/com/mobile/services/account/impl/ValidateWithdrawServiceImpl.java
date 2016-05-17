package com.mobile.services.account.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.mobile.domain.account.Credit;
import com.mobile.services.account.ValidateWithdrawService;

public abstract class ValidateWithdrawServiceImpl extends Service implements ValidateWithdrawService{

    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }


    public class RetrieveAccountInfoLocalBinder extends Binder {
        public ValidateWithdrawServiceImpl getService()
        {
            return  ValidateWithdrawServiceImpl.this;
        }
    }

    public ValidateWithdrawServiceImpl() {
    }

    @Override
    public boolean isValidWithdraw(Credit account, double withdrawRequest) {
        double balance = account.getBalance();
        double limit = account.getDailyLimit();

        if(balance < withdrawRequest || limit < withdrawRequest)
            return  false;
        else
            return true;
    }
}
