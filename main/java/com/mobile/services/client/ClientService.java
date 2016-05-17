package com.mobile.services.client;

import android.content.Context;

import com.mobile.domain.client.Client;

/**
 * Created by Isiphile on 2016-04-20.
 */
public interface ClientService {
    void addClient(Context context, Client client);
    void updateClient(Context context, Client client);
    void deleteClient(Context context, Client client);
}
