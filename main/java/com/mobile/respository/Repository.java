package com.mobile.respository;

import com.mobile.domain.account.Cheque;
import com.mobile.domain.account.Credit;
import com.mobile.domain.account.Savings;
import com.mobile.domain.client.Client;
import com.mobile.domain.client.ClientAddress;
import com.mobile.domain.client.ClientContact;
import com.mobile.domain.transaction.AirTime;
import com.mobile.domain.transaction.Payment;
import com.mobile.domain.transaction.Withdraw;

import java.util.Set;

/**
 * Created by Isiphile on 2016-04-20.
 */
public interface Repository<E, ID> {

    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}