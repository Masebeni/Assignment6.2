package com.mobile.domain.transaction;

import java.io.Serializable;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class AirTime implements Serializable {
    private Long id;
    private String account;
    private String network;
    private float amount;

    private AirTime() {

    }

    public Long getId() {return id; }
    public String getAccount() {return account;}
    public String getNetwork() {return network;}
    public float getAmount() {return amount;}

    public AirTime(Builder builder) {
        this.id = builder.id;
        this.account = builder.account;
        this.network = builder.network;
        this.amount = builder.amount;
    }

    public static class Builder {
        private Long id;
        private String account;
        private String network;
        private float amount;

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder account(String value) {
            this.account = value;
            return this;
        }
        public Builder network(String value) {
            this.network = value;
            return this;
        }

        public Builder amount(float value) {
            this.amount = value;
            return this;
        }

        public Builder copy(AirTime value) {
            this.id = value.id;
            this.account = value.account;
            this.network = value.network;
            this.amount = value.amount;
            return this;
        }

        public AirTime build() {
            return new AirTime(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AirTime airTime = (AirTime) o;

        return id != null ? id.equals(airTime.id) : airTime.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
