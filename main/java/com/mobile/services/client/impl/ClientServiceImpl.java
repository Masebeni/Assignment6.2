package com.mobile.services.client.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.mobile.config.util.App;
import com.mobile.domain.client.Client;
import com.mobile.repository.client.ClientRepository;
import com.mobile.repository.client.impl.ClientRepositoryImpl;
import com.mobile.services.client.ClientService;

import java.util.List;
import java.util.Set;


public class ClientServiceImpl extends IntentService implements ClientService{

    ClientRepository clientRepository = new ClientRepositoryImpl(App.getAppContext());
    private static final String ACTION_ADD = "com.mobile.services.client.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.mobile.services.client.impl.action.UPDATE";

    // TODO: Rename parameters
    private static final String EXTRA_ADD = "com.mobile.services.client.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.mobile.services.client.impl.extra.UPDATE";


    private static ClientServiceImpl service = null;

    public static ClientServiceImpl getInstance() {
        if (service == null)
            service = new ClientServiceImpl();
        return service;
    }

    public ClientServiceImpl() {
        super("ClientServiceImpl");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            if (intent != null) {
                final String action = intent.getAction();
                if (ACTION_ADD.equals(action)) {
                    final Client client = (Client)intent.getSerializableExtra(EXTRA_ADD);
                    saveClient(client);
                }
                else if(ACTION_UPDATE.equals(action)) {
                    final Client client = (Client)intent.getSerializableExtra(EXTRA_ADD);
                    clientUpdate(client);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addClient(Context context, Client client) {
        Intent intent = new Intent(context, ClientServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, client);
        context.startService(intent);
    }

    @Override
    public void updateClient(Context context, Client client) {

        Intent intent = new Intent(context, ClientServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, client);
        context.startService(intent);
    }
    private void saveClient(Client client) {
        ClientRepository personRepository= new ClientRepositoryImpl(getBaseContext());
        personRepository.save(client);
    }

    @Override
    public void deleteClient(Context context, Client client) {
        ClientRepository personRepository= new ClientRepositoryImpl(getBaseContext());
        personRepository.delete(client);
    }

    private void clientUpdate(Client client)
    {
        ClientRepository personRepository= new ClientRepositoryImpl(getBaseContext());
        personRepository.update(client);
    }
}
