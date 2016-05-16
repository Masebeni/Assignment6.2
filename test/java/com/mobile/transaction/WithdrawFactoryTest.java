package com.mobile.transaction;

import com.mobile.domain.transaction.Withdraw;
import com.mobile.factories.transaction.WithdrawFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class WithdrawFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Withdraw withdraw = WithdrawFactory.getWithdraw(101, "savings", 200);
        Assert.assertEquals(withdraw.getFromAccount(),"savings");
    }

    @Test public void testUpdate() throws Exception {
        Withdraw withdraw = WithdrawFactory.getWithdraw(101, "current", 500);
        Withdraw newWithdraw = new Withdraw
            .Builder()
            .copy(withdraw)
            .fromAccount("credit")
            .build();
    Assert.assertEquals(newWithdraw.getFromAccount(),"credit");
    }
}
