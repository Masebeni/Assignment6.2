package com.mobile;

import com.mobile.account.ChequeFactoryTest;
import com.mobile.account.CreditFactoryTest;
import com.mobile.account.SavingsFactoryTest;
import com.mobile.bank.BankContactDetailsFactoryTest;
import com.mobile.bank.BankFactoryTest;
import com.mobile.client.ClientAddressFactoryTest;
import com.mobile.client.ClientContactFactoryTest;
import com.mobile.client.ClientFactoryTest;
import com.mobile.transaction.AirTimeFactoryTest;
import com.mobile.transaction.PaymentFactoryTest;
import com.mobile.transaction.WithdrawFactoryTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClientAddressFactoryTest.class,
        ClientContactFactoryTest.class,
        ClientFactoryTest.class,
        ChequeFactoryTest.class,
        CreditFactoryTest.class,
        SavingsFactoryTest.class,
        BankFactoryTest.class,
        BankContactDetailsFactoryTest.class,
        AirTimeFactoryTest.class,
        PaymentFactoryTest.class,
        WithdrawFactoryTest.class,})
public class TestSuite {}