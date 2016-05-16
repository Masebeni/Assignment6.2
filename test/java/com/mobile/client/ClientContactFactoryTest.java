package com.mobile.client;

import com.mobile.domain.client.ClientContact;
import com.mobile.factories.client.ClientContactFactory;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class ClientContactFactoryTest {@Test
                                       public void testUpdate() throws Exception {
    ClientContact clientContact = ClientContactFactory.getContact(101, "078", "axe@gmail.com");
    ClientContact newClientContact = new ClientContact
            .Builder()
            .copy(clientContact)
            .email("axe@cput.ac.za")
            .build();
    Assert.assertEquals(newClientContact.getEmail(),"axe@cput.ac.za");

}
    @Test
    public void testCreate() throws Exception {
        ClientContact clientContact = ClientContactFactory.getContact(101, "078", "axe@gmail.com");
        Assert.assertEquals(clientContact.getCellNumber(),"078");
    }


}
