package com.mobile.bank;

import com.mobile.domain.bank.Bank;
import com.mobile.factories.bank.BankFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class BankFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Bank bank = BankFactory.getBank(101, "absa", "durban");
        Assert.assertEquals(bank.getBranch(),"durban");
    }

    @Test
    public void testUpdate() throws Exception {
        Bank bank = BankFactory.getBank(101, "nedbank", "East London");
        Bank newBank = new Bank
                .Builder()
                .copy(bank)
                .name("fnb")
                .build();
        Assert.assertEquals(newBank.getName(),"fnb");

    }
}
