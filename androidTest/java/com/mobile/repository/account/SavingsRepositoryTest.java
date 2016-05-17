package com.mobile.repository.account;

import android.database.SQLException;
import android.test.AndroidTestCase;

import com.mobile.domain.account.Savings;
import com.mobile.repository.account.impl.SavingsRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 *  Created by Isiphile on 2016-04-20.
 */
public class SavingsRepositoryTest extends AndroidTestCase {
    private static final String TAG="SAVING TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        SavingsRepository repo = new SavingsRepositoryImpl(this.getContext()) {
            @Override
            public void open() throws SQLException {
                super.open();
            }
        };
        // CREATE
        Savings createEntity = new Savings.Builder()
                .id((long) 1001)
                .name("credit")
                .balance(2000)
                .build();
        Savings insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Savings> savingses = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",savingses.size()>0);

        //READ ENTITY
        Savings entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Savings updateEntity = new Savings.Builder()
                .id((long) 1002)
                .name("savings")
                .balance(10000)
                .build();
        repo.update(updateEntity);
        Savings newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","savings",newEntity.getName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Savings deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
