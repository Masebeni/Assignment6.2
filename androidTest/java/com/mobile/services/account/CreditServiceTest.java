package com.mobile.services.account;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.mobile.domain.account.Credit;
import com.mobile.repository.account.CreditRepository;
import com.mobile.repository.account.impl.CreditRepositoryImpl;


public class CreditServiceTest extends IntentService {

    private static final String ACTION_ADD = "com.mobile.services.account.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.mobile.services.account.impl.action.UPDATE";
    private static final String ACTION_DELETE = "com.mobile.services.account.impl.action.DELETE";

    private static final String EXTRA_ADD = "com.mobile.services.account.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.mobile.services.account.impl.extra.UPDATE";
    private static final String EXTRA_DELETE = "com.mobile.services.account.impl.extra.DELETE";

    public CreditServiceTest() {
        super("ChequeServiceTest");
    }

    private static CreditServiceTest service = null;

    public static CreditServiceTest getInstance() {
        if (service == null)
            service = new CreditServiceTest();
        return service;
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Credit credit = (Credit)intent.getSerializableExtra(EXTRA_ADD);
                insertCreditAccount(credit);
            } else if (ACTION_UPDATE.equals(action)) {
                final Credit credit = (Credit)intent.getSerializableExtra(EXTRA_UPDATE);
                updateCreditAccount(credit);
            }
            else if (ACTION_DELETE.equals(action)) {
                final Credit credit = (Credit)intent.getSerializableExtra(EXTRA_DELETE);
                deleteCreditAccount(credit);
            }
        }
    }

    public static void startActionInsert(Context context, Credit credit) {
        Intent intent = new Intent(context, CreditServiceTest.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, credit);
        context.startService(intent);
    }

    public static void startActionUpdate(Context context, Credit credit) {
        Intent intent = new Intent(context, CreditServiceTest.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, credit);
        context.startService(intent);
    }

    public static void startActionDelete(Context context, Credit credit) {
        Intent intent = new Intent(context, CreditServiceTest.class);
        intent.setAction(ACTION_DELETE);
        intent.putExtra(EXTRA_DELETE, credit);
        context.startService(intent);
    }

    private void insertCreditAccount(Credit credit) {
        CreditRepository creditRepository = new CreditRepositoryImpl(getBaseContext());
        creditRepository.save(credit);
    }

    private void updateCreditAccount(Credit credit) {
        CreditRepository creditRepository = new CreditRepositoryImpl(getBaseContext());
        creditRepository.update(credit);
    }

    private void deleteCreditAccount(Credit credit) {
        CreditRepository creditRepository = new CreditRepositoryImpl(getBaseContext());
        creditRepository.delete(credit);
    }
}
