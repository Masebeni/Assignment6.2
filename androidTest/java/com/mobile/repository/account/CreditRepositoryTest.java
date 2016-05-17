package com.mobile.repository.account;

import android.database.SQLException;
import android.test.AndroidTestCase;

import com.mobile.domain.account.Credit;
import com.mobile.repository.account.impl.CreditRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 *  Created by Isiphile on 2016-04-20.
 */
public class CreditRepositoryTest extends AndroidTestCase {
    private static final String TAG="CREDIT TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        CreditRepository repo = new CreditRepositoryImpl(this.getContext()) {
            @Override
            public void open() throws SQLException {
                super.open();
            }
        };
        // CREATE
        Credit createEntity = new Credit.Builder()
                .id((long)1001)
                .name("credit")
                .dailyLimit(2000)
                .creditLimit(5000)
                .build();
        Credit insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Credit> credits = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",credits.size()>0);

        //READ ENTITY
        Credit entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Credit updateEntity = new Credit.Builder()
                .id((long) 1002)
                .name("current")
                .dailyLimit(10000)
                .build();
        repo.update(updateEntity);
        Credit newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","current",newEntity.getName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Credit deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
