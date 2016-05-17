package com.mobile.services.account.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.mobile.domain.account.Cheque;
import com.mobile.repository.account.ChequeRepository;
import com.mobile.repository.account.impl.ChequeRepositoryImpl;
import com.mobile.services.account.ChequeAccountDetails;


public abstract class ChequeAccountDetailsImpl extends Service implements ChequeAccountDetails {

    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public ChequeAccountDetailsImpl() {
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
        public ChequeAccountDetailsImpl getService()
        {
            return  ChequeAccountDetailsImpl.this;
        }
    }
}
