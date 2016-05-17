package com.mobile.services.account;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.mobile.domain.account.Cheque;
import com.mobile.repository.account.ChequeRepository;
import com.mobile.repository.account.impl.ChequeRepositoryImpl;


public class ChequeServiceTest extends IntentService {

    private static final String ACTION_ADD = "com.mobile.services.account.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.mobile.services.account.impl.action.UPDATE";
    private static final String ACTION_DELETE = "com.mobile.services.account.impl.action.DELETE";

    private static final String EXTRA_ADD = "com.mobile.services.account.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.mobile.services.account.impl.extra.UPDATE";
    private static final String EXTRA_DELETE = "com.mobile.services.account.impl.extra.DELETE";

    public ChequeServiceTest() {
        super("ChequeServiceTest");
    }

    private static ChequeServiceTest service = null;

    public static ChequeServiceTest getInstance() {
        if (service == null)
            service = new ChequeServiceTest();
        return service;
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Cheque cheque = (Cheque)intent.getSerializableExtra(EXTRA_ADD);
                insertChequeAccount(cheque);
            } else if (ACTION_UPDATE.equals(action)) {
                final Cheque cheque = (Cheque)intent.getSerializableExtra(EXTRA_UPDATE);
                updateChequeAccount(cheque);
            }
            else if (ACTION_DELETE.equals(action)) {
                final Cheque cheque = (Cheque)intent.getSerializableExtra(EXTRA_DELETE);
                deleteChequeAccount(cheque);
            }
        }
    }

    public static void startActionInsert(Context context, Cheque cheque) {
        Intent intent = new Intent(context, ChequeServiceTest.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, cheque);
        context.startService(intent);
    }

    public static void startActionUpdate(Context context, Cheque cheque) {
        Intent intent = new Intent(context, ChequeServiceTest.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, cheque);
        context.startService(intent);
    }


    public static void startActionDelete(Context context, Cheque cheque) {
        Intent intent = new Intent(context, ChequeServiceTest.class);
        intent.setAction(ACTION_DELETE);
        intent.putExtra(EXTRA_DELETE, cheque);
        context.startService(intent);
    }

    private void insertChequeAccount(Cheque cheque) {
        ChequeRepository chequeRepository = new ChequeRepositoryImpl(getBaseContext());
        chequeRepository.save(cheque);
    }

    private void updateChequeAccount(Cheque cheque) {
        ChequeRepository chequeRepository = new ChequeRepositoryImpl(getBaseContext());
        chequeRepository.update(cheque);
    }

    private void deleteChequeAccount(Cheque cheque) {
        ChequeRepository chequeRepository = new ChequeRepositoryImpl(getBaseContext());
        chequeRepository.delete(cheque);
    }
}
