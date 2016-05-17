package com.mobile.services.account;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.mobile.domain.account.Credit;

public abstract class ValidateWithdrawServiceTest extends Service implements ValidateWithdrawService{

    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }


    public class RetrieveAccountInfoLocalBinder extends Binder {
        public ValidateWithdrawServiceTest getService()
        {
            return  ValidateWithdrawServiceTest.this;
        }
    }

    public ValidateWithdrawServiceTest() {
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
