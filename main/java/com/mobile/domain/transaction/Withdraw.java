package com.mobile.domain.transaction;

import java.io.Serializable;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class Withdraw implements Serializable {
    private Long id;
    private String fromAccount;
    private float amount;


    private Withdraw() {

    }

    public Long getId() {return id; }
    public String getFromAccount() {return fromAccount;}
    public float getAmount() {return amount;}

    public Withdraw(Builder builder) {
        this.id = builder.id;
        this.fromAccount = builder.fromAccount;
        this.amount = builder.amount;
    }

    public static class Builder {
        private Long id;
        private String fromAccount;
        private float amount;

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder fromAccount(String value) {
            this.fromAccount = value;
            return this;
        }

        public Builder amount(float value) {
            this.amount = value;
            return this;
        }


        public Builder copy(Withdraw value) {
            this.id = value.id;
            this.fromAccount = value.fromAccount;
            this.amount = value.amount;
            return this;
        }

        public Withdraw build() {
            return new Withdraw(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Withdraw withdraw = (Withdraw) o;

        return id != null ? id.equals(withdraw.id) : withdraw.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
