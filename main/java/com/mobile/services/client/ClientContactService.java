package com.mobile.services.client;

import android.content.Context;

import com.mobile.domain.client.ClientAddress;
import com.mobile.domain.client.ClientContact;

/**
 * Created by Isiphile on 2016-05-12.
 */
public interface ClientContactService {
    void addClient(Context context, ClientAddress client);
    void deleteClient(Context context, ClientAddress client);
}
