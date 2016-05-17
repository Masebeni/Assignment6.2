package com.mobile.repository.client;

import android.database.SQLException;
import android.test.AndroidTestCase;

import com.mobile.domain.client.ClientAddress;
import com.mobile.repository.client.impl.ClientAddressRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class ClientAddressRepositoryTest extends AndroidTestCase {

    private static final String TAG = "ADDRESS TEST";
    private Long id;

    public void testCreateReadUpdate() throws Exception
    {
        ClientAddressRepository repo = new ClientAddressRepositoryImpl(this.getContext()) {
            @Override
            public void open() throws SQLException {
                super.open();
            }
        };
        // CREATE
        ClientAddress createEntity = new ClientAddress.Builder()
                .id((long) 1007)
                .address("woodstock")
                .town("cpt")
                .postalCode("8000")
                .build();
        ClientAddress insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE ",insertedEntity);

        //READ ENTITY
        ClientAddress entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<ClientAddress> clients = repo.findAll();
        Assert.assertTrue(TAG+ "READ ENTITY",clients.size() >0);

        //UPDATE ENTITY
        ClientAddress updateEntity = new ClientAddress.Builder()
                .copy(entity)
                .town("QTN")
                .build();

        repo.update(updateEntity);
        ClientAddress newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", "QTN",newEntity.getTown());

        //DELTE ENTITY
        repo.delete(updateEntity);
        ClientAddress deleteEntity = repo.findById(id);
        Assert.assertNull(TAG +"DELETE", deleteEntity);

        repo.deleteAll();

    }
}
