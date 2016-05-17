package com.mobile.repository.client;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import com.mobile.domain.client.Client;
import com.mobile.repository.client.impl.ClientRepositoryImpl;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class ClientRepositoryTest  extends AndroidTestCase {

    private static final String TAG = "CLIENT TEST";
    private Long id;

    public void testCreateReadUpdate() throws Exception
    {
        ClientRepository repo = new ClientRepositoryImpl(this.getContext());
        // CREATE
        Client createEntity = new Client.Builder()
                .id((long) 1007)
                .firstName("v")
                .lastName("Vuyo")
                .password("1007")
                .build();
        Client insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE ",insertedEntity);

        //READ ENTITY
        Client entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<Client> clients = repo.findAll();
        Assert.assertTrue(TAG+ "READ ENTITY",clients.size() >0);

        //UPDATE ENTITY
        Client updateEntity = new Client.Builder()
                .copy(entity)
                .firstName("Vuyolwethu")
                .build();

        repo.update(updateEntity);
        Client newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", "Vuyolwethu",newEntity.getFirstName());

        //DELTE ENTITY
        repo.delete(updateEntity);
        Client deleteEntity = repo.findById(id);
        Assert.assertNull(TAG +"DELETE", deleteEntity);

        repo.deleteAll();

    }
}
