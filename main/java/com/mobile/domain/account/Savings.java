package com.mobile.domain.account;

import java.io.Serializable;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class Savings implements Serializable {
    private Long id;
    private String name;
    private float balance;


    private Savings() {

    }

    public Long getId() {return id; }
    public String getName() {return name;}
    public float getBalance() {return balance;}

    public Savings(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.balance = builder.balance;
    }

    public static class Builder {
        private Long id;
        private String name;
        private float balance;

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder balance(float value) {
            this.balance = value;
            return this;
        }


        public Builder copy(Savings value) {
            this.id = value.id;
            this.name = value.name;
            this.balance = value.balance;
            return this;
        }

        public Savings build() {
            return new Savings(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Savings savings = (Savings) o;

        return id != null ? id.equals(savings.id) : savings.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
