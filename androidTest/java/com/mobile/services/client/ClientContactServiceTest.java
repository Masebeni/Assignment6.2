package com.mobile.services.client;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.mobile.domain.client.ClientContact;
import com.mobile.repository.client.ClientContactRepository;
import com.mobile.repository.client.impl.ClientContactRepositoryImpl;

public class ClientContactServiceTest extends IntentService {

    private static final String ACTION_ADD = "com.mobile.services.client.impl.action.ADD";
    private static final String ACTION_DELETE = "com.mobile.services.client.impl.action.DELETE";

    // TODO: Rename parameters
    private static final String EXTRA_ADD = "com.mobile.services.client.impl.extra.ADD";
    private static final String EXTRA_DELETE = "com.mobile.services.client.impl.extra.DELETE";

    public ClientContactServiceTest() {
        super("ClientServiceImpl");
    }

    private static ClientContactServiceTest service = null;

    public static ClientContactServiceTest getInstance() {
        if (service == null)
            service = new ClientContactServiceTest();
        return service;
    }
    public static void startActionAdd(Context context,ClientContact clientContact) {
        Intent intent = new Intent(context, ClientContactServiceTest.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, clientContact);
        context.startService(intent);
    }

    public static void startActionDelete(Context context, ClientContact clientContact) {
        Intent intent = new Intent(context, ClientContactServiceTest.class);
        intent.setAction(ACTION_DELETE);
        intent.putExtra(EXTRA_DELETE, clientContact);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final ClientContact clientContact = (ClientContact)intent.getSerializableExtra(EXTRA_ADD);
                insert(clientContact);
            } else if (ACTION_DELETE.equals(action)) {
                final ClientContact clientContact = (ClientContact)intent.getSerializableExtra(EXTRA_ADD);
                delete(clientContact);
            }
        }
    }

    private void insert(ClientContact clientContact) {
        ClientContactRepository clientRepository = new ClientContactRepositoryImpl(getBaseContext());
        clientRepository.save(clientContact);
    }

    private void delete(ClientContact clientContact) {
        ClientContactRepository studentRepository = new ClientContactRepositoryImpl(getBaseContext());
        studentRepository.delete(clientContact);
    }
}
