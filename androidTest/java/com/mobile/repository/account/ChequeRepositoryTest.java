package com.mobile.repository.account;

import android.database.SQLException;
import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import com.mobile.domain.account.Cheque;
import com.mobile.respository.account.Impl.ChequeRepositoryImpl;
import com.mobile.respository.account.ChequeRepository;

/**
 *  Created by Isiphile on 2016-04-20.
 */
public class ChequeRepositoryTest extends AndroidTestCase {
    private static final String TAG="HEQUE TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ChequeRepository repo = new ChequeRepositoryImpl(this.getContext());
        // CREATE
        Cheque createEntity = new Cheque.Builder()
                .id((long)1001)
                .name("cheque")
                .dailyLimit(2000)
                .build();
        Cheque insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Cheque> cheques = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",cheques.size()>0);

        //READ ENTITY
        Cheque entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Cheque updateEntity = new Cheque.Builder()
                .id((long) 1002)
                .name("current")
                .dailyLimit(5000)
                .build();
        repo.update(updateEntity);
        Cheque newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","current",newEntity.getName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Cheque deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
