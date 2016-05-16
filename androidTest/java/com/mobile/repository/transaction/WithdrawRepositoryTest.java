package com.mobile.repository.transaction;

import android.test.AndroidTestCase;

import com.mobile.domain.transaction.Withdraw;
import com.mobile.respository.transaction.Impl.WithdrawRepositoryImpl;
import com.mobile.respository.transaction.WithdrawRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class WithdrawRepositoryTest extends AndroidTestCase {

    private static final String TAG = "payment";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        WithdrawRepository repo = new WithdrawRepositoryImpl(this.getContext());
        // CREATE
        Withdraw createEntity = new Withdraw.Builder()
                .id((long)1003)
                .fromAccount("current")
                .amount(100)
                .build();
        Withdraw insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ENTITY
        Withdraw entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<Withdraw> withdraws = repo.findAll();
        Assert.assertTrue(TAG+ "READ ENTITY",withdraws.size() >0);

        //UPDATE ENTITY
        Withdraw updateEntity = new Withdraw.Builder()
                .fromAccount("current")
                .amount(50)
                .build();

        repo.update(updateEntity);
        Withdraw newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", "current",newEntity.getFromAccount());

        //DELTE ENTITY
        repo.delete(updateEntity);
        Withdraw deleteEntity = repo.findById(id);
        Assert.assertNull(TAG +"DELETE", deleteEntity);

        repo.deleteAll();
    }
}
