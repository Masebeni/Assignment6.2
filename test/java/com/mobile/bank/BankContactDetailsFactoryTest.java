package com.mobile.bank;

import com.mobile.domain.bank.BankContactDetails;
import com.mobile.factories.bank.BankContactDetailsFactory;
import com.mobile.factories.bank.BankFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class BankContactDetailsFactoryTest {
    @Test
    public void testCreate() throws Exception {
        BankContactDetails bankContactDetails = BankContactDetailsFactory.getBankContactDetails(101, "021", "@capetown");
        Assert.assertEquals(bankContactDetails.getPhoneNumber(),"021");
    }

    @Test
    public void testUpdate() throws Exception {
        BankContactDetails bankContactDetails = BankContactDetailsFactory.getBankContactDetails(103, "047", "@queenstown");

        BankContactDetails newBankContactDetails = new BankContactDetails
                .Builder()
                .copy(bankContactDetails)
                .email("@durban")
                .build();
        Assert.assertEquals(newBankContactDetails.getEmail(),"@durban");

    }
}
