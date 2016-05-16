package com.mobile.repository.transaction;

import android.database.SQLException;
import android.test.AndroidTestCase;

import com.mobile.domain.transaction.Payment;
import com.mobile.respository.transaction.Impl.PaymentRepositoryImpl;
import com.mobile.respository.transaction.PaymentRepository;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class PaymentRepositoryTest extends AndroidTestCase {

    private static final String TAG = "payment";
    private Long id;

    public void testCreateReadUpdate() throws Exception
    {
        PaymentRepository repo = new PaymentRepositoryImpl(this.getContext());

        //CREATE
        Payment createEntity = new Payment.Builder()
                .id((long) 1001)
                .fromAccount("cheque")
                .toAccount("credit")
                .amount(300)
                .build();
      Payment insertedEntity = repo.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE ",insertedEntity);

        //READ ENTITY
        Payment entity = repo.findById(id);
        Assert.assertNotNull(TAG + "READ ENTITY", entity);

        //READ ALL
        Set<Payment> payments = repo.findAll();
        Assert.assertTrue(TAG+ "READ ENTITY",payments.size() >0);

        //UPDATE ENTITY
        Payment updateEntity = new Payment.Builder()
                .fromAccount("current")
                .toAccount("savings")
                .amount(200)
                .build();

        repo.update(updateEntity);
        Payment newEntity = repo.findById(id);
        Assert.assertEquals(TAG + "UPDATE ENTITY", "savings",newEntity.getToAccount());

        //DELTE ENTITY
        repo.delete(updateEntity);
        Payment deleteEntity = repo.findById(id);
        Assert.assertNull(TAG +"DELETE", deleteEntity);

        repo.deleteAll();
    }
}
