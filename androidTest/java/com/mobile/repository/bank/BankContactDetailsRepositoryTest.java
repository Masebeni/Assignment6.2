package com.mobile.repository.bank;

import android.test.AndroidTestCase;

import com.mobile.domain.bank.BankContactDetails;
import com.mobile.repository.bank.Impl.BankContactDetailsRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class BankContactDetailsRepositoryTest extends AndroidTestCase {
    private static final String TAG="BANK TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        BankContactDetailsRepository repo = new BankContactDetailsRepositoryImpl(this.getContext()) {
        };
        // CREATE
        BankContactDetails createEntity = new BankContactDetails.Builder()
                .phoneNumber("021")
                .email("@gmail.com")
                .build();
        BankContactDetails insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<BankContactDetails> bankContactDetails = repo.findAll();
        Assert.assertTrue(TAG + " READ ALL", bankContactDetails.size() > 0);

        //READ ENTITY
        BankContactDetails entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);


        //UPDATE ENTITY
        BankContactDetails updateEntity = new BankContactDetails.Builder()
                .phoneNumber("023")
                .email("@yahoo.com")
                .build();
        repo.update(updateEntity);
        BankContactDetails newBankContacts = repo.findById(id);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "@yahoo.com", newBankContacts.getEmail());

        // DELETE ENTITY
        repo.delete(updateEntity);
        BankContactDetails deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
