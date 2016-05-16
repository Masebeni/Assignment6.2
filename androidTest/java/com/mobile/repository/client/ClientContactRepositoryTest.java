package com.mobile.repository.client;

import android.database.SQLException;
import android.test.AndroidTestCase;

import com.mobile.domain.client.ClientContact;
import com.mobile.respository.client.ClientContactRepository;
import com.mobile.respository.client.Impl.ClientContactRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class ClientContactRepositoryTest extends AndroidTestCase {

    private static final String TAG = "CONTACT TEST";
    private Long id;

    public void testCreateReadUpdate() throws Exception
    {
        ClientContactRepository repo = new ClientContactRepositoryImpl(this.getContext()) {
            @Override
            public void open() throws SQLException {
                super.open();
            }
        };
        // CREATE
        ClientContact createEntity = new ClientContact.Builder()
                .id((long) 1008)
                .cellNumber("078")
                .email("@gmail")
                .build();
        ClientContact insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE ",insertedEntity);

        //READ ENTITY
        ClientContact entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<ClientContact> clients = repo.findAll();
        Assert.assertTrue(TAG+ "READ ENTITY",clients.size() >0);

        //UPDATE ENTITY
        ClientContact updateEntity = new ClientContact.Builder()
                .copy(entity)
                .email("@cput")
                .build();

        repo.update(updateEntity);
        ClientContact newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", "@cput",newEntity.getEmail());

        //DELTE ENTITY
        repo.delete(updateEntity);
        ClientContact deleteEntity = repo.findById(id);
        Assert.assertNull(TAG +"DELETE", deleteEntity);

        repo.deleteAll();

    }
}
