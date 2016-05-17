package com.mobile.factories.transaction;


import com.mobile.domain.transaction.Withdraw;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class WithdrawFactory{
    public static Withdraw getWithdraw(long identity, String from, float money){
        return  new Withdraw.Builder()
                .id(identity)
                .fromAccount(from)
                .amount(money)
                .build();
    }
    public static String getWithdrawal(String transaction) {
        return "Withdrawal";
    }
}
