package com.mobile.factories.client;

import com.mobile.domain.client.Client;


/**
 * Created by Isiphile on 2016-04-20.
 */
public class ClientFactory {
    public static Client getClient(long identity,String name,String surname,String password){
        return new Client.Builder()
                .id(identity )
                .firstName(name)
                .lastName(surname)
                .password(password)
                .build();
    }
}