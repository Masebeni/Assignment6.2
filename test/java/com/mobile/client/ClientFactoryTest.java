package com.mobile.client;

import com.mobile.domain.client.Client;
import com.mobile.factories.client.ClientFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class ClientFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Client client = ClientFactory.getClient(101,"axe","mase","123");
        Assert.assertEquals(client.getId(),1,101);
    }

    @Test
    public void testUpdate() throws Exception {
        Client client = ClientFactory.getClient(101,"siya","mbuya","321");
        Client newClient = new Client
                .Builder()
                .copy(client)
                .firstName("axe")
                .build();
        Assert.assertEquals(newClient.getFirstName(),"axe");

    }
}
