package com.mobile.services.bank;

import android.content.Context;

import com.mobile.domain.account.Cheque;
import com.mobile.domain.bank.Bank;

/**
 * Created by Isiphile on 2016-05-12.
 */
public interface BankService {
    void addBank(Context context, Bank bank );
    void updateBank(Context context, Bank bank);
}
