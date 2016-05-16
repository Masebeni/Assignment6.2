package com.mobile.repository.transaction;

import android.database.SQLException;
import android.test.AndroidTestCase;

import com.mobile.domain.client.Client;
import com.mobile.domain.transaction.AirTime;
import com.mobile.respository.client.ClientRepository;
import com.mobile.respository.client.Impl.ClientRepositoryImpl;
import com.mobile.respository.transaction.AirTimeRepository;
import com.mobile.respository.transaction.Impl.AirTimeRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class AirTimeRepositoryTest extends AndroidTestCase {

    private static final String TAG = "aiTime";
    private Long id;

    public void testCreateReadUpdate() throws Exception
    {
        AirTimeRepository repo = new AirTimeRepositoryImpl(this.getContext());
        //CREATE
        AirTime createEntity = new AirTime.Builder()
                .id((long) 1001)
                .account("cheque")
                .network("mtn")
                .amount(30)
                .build();
      AirTime insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE ",insertedEntity);

        //READ ENTITY
        AirTime entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<AirTime> clients = repo.findAll();
        Assert.assertTrue(TAG+ "READ ENTITY",clients.size() >0);

        //UPDATE ENTITY
        AirTime updateEntity = new AirTime.Builder()
                .account("current")
                .network("mtn")
                .amount(60)
                .build();

        repo.update(updateEntity);
        AirTime newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", "current",newEntity.getAccount());

        //DELTE ENTITY
        repo.delete(updateEntity);
        AirTime deleteEntity = repo.findById(id);
        Assert.assertNull(TAG +"DELETE", deleteEntity);

        repo.deleteAll();
    }
}
