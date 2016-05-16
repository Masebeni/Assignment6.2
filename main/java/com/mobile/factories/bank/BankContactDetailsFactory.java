package com.mobile.factories.bank;

import com.mobile.domain.bank.BankContactDetails;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class BankContactDetailsFactory {
    public static BankContactDetails getBankContactDetails(long identity,String number, String mail){
        return new BankContactDetails.Builder()
                .id(identity)
                .phoneNumber(number)
                .email(mail)
                .build();
    }
}
