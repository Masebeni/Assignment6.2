package com.mobile.domain.client;

import java.io.Serializable;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class ClientContact implements Serializable {
    private Long id;
    private String cellNumber;
    private String email;
    private ClientContact(){}

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public ClientContact(Builder builder) {
        this.id = builder.id;
        this.cellNumber = builder.cellNumber;
        this.email = builder.email;
    }

    public static class Builder{
        private Long id;
        private String cellNumber;
        private String email;

        public Builder id(Long value){
            this.id =value;
            return this;
        }

        public Builder cellNumber(String value){
            this.cellNumber =value;
            return this;
        }

        public Builder email(String value){
            this.email =value;
            return this;
        }

        public Builder copy(ClientContact value){
            this.id = value.id;
            this.cellNumber= value.cellNumber;
            this.email=value.email;
            return  this;
        }

        public ClientContact build(){
            return new ClientContact(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientContact clientContact = (ClientContact) o;

        return id.equals(clientContact.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
