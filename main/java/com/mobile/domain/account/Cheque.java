package com.mobile.domain.account;

import java.io.Serializable;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class Cheque implements Serializable {
    private Long id;
    private String name;
    private float balance;
    private float dailyLimit;

    private Cheque() {

    }

    public Long getId() {return id; }
    public String getName() {return name;}
    public float getBalance() {return balance;}
    public float getDailyLimit() {return dailyLimit;}

    public Cheque(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.balance = builder.balance;
        this.dailyLimit = builder.dailyLimit;
    }

    public static class Builder {
        private Long id;
        private String name;
        private float balance;
        private float dailyLimit;

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
        public Builder dailyLimit(float value) {
            this.dailyLimit = value;
            return this;
        }

        public Builder copy(Cheque value) {
            this.id = value.id;
            this.name = value.name;
            this.balance = value.balance;
            this.dailyLimit = value.dailyLimit;
            return this;
        }

        public Cheque build() {
            return new Cheque(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cheque cheque = (Cheque) o;

        return id != null ? id.equals(cheque.id) : cheque.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
