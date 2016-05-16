package com.mobile.account;

import com.mobile.domain.account.Credit;
import com.mobile.factories.account.CreditFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class CreditFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Credit credit = CreditFactory.getCredit(101, "credit", 10000, 2000, 5000);
        Assert.assertEquals(credit.getName(),"credit");
    }

    @Test
    public void testUpdate() throws Exception {
        Credit credit = CreditFactory.getCredit(102, "credit", 20000, 4000, 7000);

        Credit newCredit = new Credit
                .Builder()
                .creditLimit(8000)
                .copy(credit)
                .build();
        Assert.assertEquals(newCredit.getCreditLimit(),1,8000);
    }
}
