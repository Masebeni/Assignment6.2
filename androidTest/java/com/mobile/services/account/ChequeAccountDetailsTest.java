package com.mobile.services.account;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.mobile.domain.account.Cheque;
import com.mobile.repository.account.ChequeRepository;
import com.mobile.repository.account.impl.ChequeRepositoryImpl;


public abstract class ChequeAccountDetailsTest extends Service implements ChequeAccountDetails {

    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public ChequeAccountDetailsTest() {
    }

    @Override
    public Cheque getAccountInfo(Cheque account) {
        ChequeRepository chequeRepository= new ChequeRepositoryImpl(getBaseContext());

        if(true)
        {
            Cheque cheque = chequeRepository.findById(account.getId());
            return cheque;
        }
        else
            return null;
    }

    public class RetrieveAccountInfoLocalBinder extends Binder {
        public ChequeAccountDetailsTest getService()
        {
            return  ChequeAccountDetailsTest.this;
        }
    }
}
