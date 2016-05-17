package com.mobile.services.bank.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.mobile.domain.account.Cheque;
import com.mobile.domain.bank.Bank;
import com.mobile.repository.account.ChequeRepository;
import com.mobile.repository.account.impl.ChequeRepositoryImpl;
import com.mobile.repository.bank.BankRepository;
import com.mobile.repository.bank.Impl.BankRepositoryImpl;


public class BankServiceImpl extends IntentService {

    private static final String ACTION_ADD = "com.mobile.services.bank.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.mobile.services.bank.impl.action.UPDATE";
    private static final String ACTION_DELETE = "com.mobile.services.bank.impl.action.DELETE";

    private static final String EXTRA_ADD = "com.mobile.services.bank.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.mobile.services.bank.impl.extra.UPDATE";
    private static final String EXTRA_DELETE = "com.mobile.services.bank.impl.extra.DELETE";

    public BankServiceImpl() {
        super("ChequeServiceImpl");
    }

    private static BankServiceImpl service = null;

    public static BankServiceImpl getInstance() {
        if (service == null)
            service = new BankServiceImpl();
        return service;
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Bank bank = (Bank)intent.getSerializableExtra(EXTRA_ADD);
                insertBank(bank);
            } else if (ACTION_UPDATE.equals(action)) {
                final Bank bank = (Bank)intent.getSerializableExtra(EXTRA_UPDATE);
                updateBank(bank);
            }
            else if (ACTION_DELETE.equals(action)) {
                final Bank bank = (Bank)intent.getSerializableExtra(EXTRA_DELETE);
                deleteBank(bank);
            }
        }
    }

    public static void startActionInsert(Context context, Bank bank) {
        Intent intent = new Intent(context, BankServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, bank);
        context.startService(intent);
    }

    public static void startActionUpdate(Context context, Bank bank) {
        Intent intent = new Intent(context, BankServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, bank);
        context.startService(intent);
    }


    public static void startActionDelete(Context context, Bank bank) {
        Intent intent = new Intent(context, BankServiceImpl.class);
        intent.setAction(ACTION_DELETE);
        intent.putExtra(EXTRA_DELETE, bank);
        context.startService(intent);
    }

    private void insertBank(Bank bank) {
        BankRepository bankRepository = new BankRepositoryImpl(getBaseContext());
        bankRepository.save(bank);
    }

    private void updateBank(Bank bank) {
        BankRepository bankRepository = new BankRepositoryImpl(getBaseContext());
        bankRepository.update(bank);
    }

    private void deleteBank(Bank bank) {
        BankRepository bankRepository = new BankRepositoryImpl(getBaseContext());
        bankRepository.delete(bank);
    }
}
