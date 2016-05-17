package com.mobile.repository.bank;


import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.Set;

import  com.mobile.domain.bank.Bank;
import com.mobile.repository.bank.Impl.BankRepositoryImpl;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class BankRepositoryTest extends AndroidTestCase{

    private static final String TAG = "BANK TEST";
    private Long id;


    public void testCreateUpdateDelete() throws Exception
    {

        BankRepository repo = new BankRepositoryImpl(this.getContext());
        // CREATE
        Bank createEntity = new Bank.Builder()
                .id((long) 1001)
                .name("capitec")
                .branch("cpt")
                .build();
        Bank insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE ", insertedEntity);


        //READ ENTITY
       Bank entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<Bank> banks = repo.findAll();
        Assert.assertTrue(TAG+ "READ ENTITY",banks.size() >0);

        //UPDATE ENTITY
        Bank updateEntity = new Bank.Builder()
                .copy(entity)
                .name("absa")
                .build();

        repo.update(updateEntity);
        Bank newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", "absa",newEntity.getName());

        //DELTE ENTITY
        repo.delete(updateEntity);
        Bank deleteEntity = repo.findById(id);
        Assert.assertNull(TAG +"DELETE", deleteEntity);

        repo.deleteAll();
    }
}
