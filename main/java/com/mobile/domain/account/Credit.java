package com.mobile.domain.account;

import java.io.Serializable;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class Credit implements Serializable {
    private Long id;
    private String name;
    private float balance;
    private  float dailyLimit;
    private float creditLimit;

    public void setId(Long id) {
        this.id = id;
    }

    private Credit() {

    }

    public Long getId() {return id; }
    public String getName() {return name;}
    public float getBalance() {return balance;}
    public float getDailyLimit() {return dailyLimit;}
    public float getCreditLimit() {return creditLimit;}

    public Credit(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.balance = builder.balance;
        this.dailyLimit = builder.dailyLimit;
        this.creditLimit = builder.creditLimit;
    }

    public static class Builder {
        private Long id;
        private String name;
        private float balance;
        private  float dailyLimit;
        private float creditLimit;

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
        public Builder creditLimit(float value) {
            this.creditLimit = value;
            return this;
        }


        public Builder copy(Credit value) {
            this.id = value.id;
            this.name = value.name;
            this.balance = value.balance;
            this.dailyLimit = value.dailyLimit;
            this.creditLimit = value.creditLimit;
            return this;
        }

        public Credit build() {
            return new Credit(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credit credit = (Credit) o;

        return id != null ? id.equals(credit.id) : credit.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
