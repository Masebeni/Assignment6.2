package com.mobile.factories.bank;

import com.mobile.domain.bank.Bank;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class BankFactory  {
    public static Bank getBank(long identity, String nam, String bank){
        return  new Bank.Builder()
                .id(identity)
                .name(nam)
                .branch(bank)
                .build();
    }
}
