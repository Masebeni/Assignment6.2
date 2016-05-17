package com.mobile.services.account;

import android.content.Context;

import com.mobile.domain.account.Credit;

/**
 * Created by Isiphile on 2016-05-12.
 */
public interface CreditService {
    void addAccount(Context context, Credit cheque);
    void updateAccount(Context context, Credit cheque);
}
