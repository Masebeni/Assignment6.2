package com.mobile.client;

import com.mobile.domain.client.ClientAddress;
import com.mobile.factories.client.ClientAddressFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class ClientAddressFactoryTest {
    @Test
    public void testCreate() throws Exception {
        ClientAddress clientAddress = ClientAddressFactory.getAddress(101, "Dorset", "Woodstock", "7989");
        Assert.assertEquals(clientAddress.getTown(),"Woodstock");
    }

    @Test
    public void testUpdate() throws Exception {
        ClientAddress clientAddress = ClientAddressFactory.getAddress(101, "Long", "Cape Town", "8000");
        ClientAddress newClientAddress= new ClientAddress
                .Builder()
                .copy(clientAddress)
                .town("Cape Town")
                .build();
        Assert.assertEquals(newClientAddress.getTown(),"Cape Town");

    }
}
