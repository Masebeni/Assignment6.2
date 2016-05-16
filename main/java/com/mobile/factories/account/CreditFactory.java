package com.mobile.factories.account;

import com.mobile.domain.account.Credit;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class CreditFactory {
   public static Credit getCredit(long identity, String nam, float bal, float dLimit, float cLimit){
       return new Credit.Builder()
               .id(identity)
               .name(nam)
               .balance(bal)
               .dailyLimit(dLimit)
               .creditLimit(cLimit)
               .build();
   }

}
