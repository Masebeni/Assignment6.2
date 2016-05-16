package com.mobile.factories.client;

import com.mobile.domain.client.ClientAddress;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class ClientAddressFactory {
    public static ClientAddress getAddress(long identity, String street, String city,String code){
        return new ClientAddress.Builder()
                .id(identity)
                .address(street)
                .town(city)
                .postalCode(code)
                .build();
    }
}