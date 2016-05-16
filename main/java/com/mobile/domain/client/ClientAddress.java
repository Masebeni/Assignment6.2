package com.mobile.domain.client;

import java.io.Serializable;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class ClientAddress implements Serializable {
    private Long id;
    private String address;
    private String town;
    private String postalCode;

    private ClientAddress(){}

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getTown() {
        return town;
    }

    public ClientAddress(Builder builder) {
        this.id = builder.id;
        this.address = builder.address;
        this.postalCode = builder.postalCode;
        this.town = builder.town;
    }

    public static class Builder{
        private Long id;
        private String address;
        private String postalCode;
        private String town;

        public Builder id(Long value){
            this.id =value;
            return this;
        }

        public Builder address(String value){
            this.address =value;
            return this;
        }

        public Builder postalCode(String value){
            this.postalCode =value;
            return this;
        }

        public Builder town(String value){
            this.town =value;
            return this;
        }

        public Builder copy(ClientAddress value){
            this.id = value.id;
            this.address= value.address;
            this.town =value.town;
            this.postalCode = value.postalCode;
            return  this;
        }

        public ClientAddress build(){
            return new ClientAddress(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientAddress clientAddress = (ClientAddress) o;

        return id.equals(clientAddress.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
