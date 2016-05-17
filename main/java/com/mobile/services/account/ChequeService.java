package com.mobile.services.account;

import android.content.Context;

import com.mobile.domain.account.Cheque;

/**
 * Created by Isiphile on 2016-05-12.
 */
public interface ChequeService {
    void addAccount(Context context, Cheque cheque);
    void updateAccount(Context context, Cheque cheque);
}
