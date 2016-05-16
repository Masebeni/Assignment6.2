package com.mobile.factories.account;

import com.mobile.domain.account.Cheque;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class ChequeFactory {
    public static Cheque getCheque(long identity, String nam, float bal, float limit) {
        return new Cheque.Builder()
                .id(identity)
                .name(nam)
                .balance(bal)
                .dailyLimit(limit)
                .build();
    }
}
