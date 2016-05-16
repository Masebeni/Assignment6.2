package com.mobile.domain.client;

import java.io.Serializable;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class Client implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;


    private Client() {

    }

    public Long getId() {return id; }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPassword() {return password; }


    public Client(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.password = builder.password;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String password;

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder firstName(String value) {
            this.firstName = value;
            return this;
        }

        public Builder lastName(String value) {
            this.lastName = value;
            return this;
        }

        public Builder password(String value) {
            this.password = value;
            return this;
        }

        public Builder copy(Client value) {
            this.id = value.id;
            this.firstName = value.firstName;
            this.lastName = value.lastName;
            this.password = value.password;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return id != null ? id.equals(client.id) : client.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
