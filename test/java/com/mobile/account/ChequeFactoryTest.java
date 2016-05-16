package com.mobile.account;

import com.mobile.domain.account.Cheque;
import com.mobile.factories.account.ChequeFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class ChequeFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Cheque cheque = ChequeFactory.getCheque(101, "cheque", 5000, 2000);
        Assert.assertEquals(cheque.getName(),"cheque");
    }

    @Test
    public void testUpdate() throws Exception {
        Cheque cheque = ChequeFactory.getCheque(101, "cheque", 10000, 5000);

        Cheque newCheque = new Cheque
                .Builder()
               .dailyLimit(3000)
                .copy(cheque)
                .build();
        Assert.assertEquals(newCheque.getDailyLimit(),1,5000);
    }
}
