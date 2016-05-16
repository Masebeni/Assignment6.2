package com.mobile.domain.transaction;

import java.io.Serializable;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class Payment implements Serializable {
    private Long id;
    private String fromAccount;
    private String toAccount;
    private float amount;


    private Payment() {

    }

    public Long getId() {return id; }
    public String getFromAccount() {return fromAccount;}
    public String getToAccount() {return toAccount;}
    public float getAmount() {return amount;}

    public Payment(Builder builder) {
        this.id = builder.id;
        this.fromAccount = builder.fromAccount;
        this.toAccount = builder.toAccount;
        this.amount = builder.amount;
    }

    public static class Builder {
        private Long id;
        private String fromAccount;
        private String toAccount;
        private float amount;

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder fromAccount(String value) {
            this.fromAccount = value;
            return this;
        }
        public Builder toAccount(String value) {
            this.toAccount = value;
            return this;
        }

        public Builder amount(float value) {
            this.amount = value;
            return this;
        }


        public Builder copy(Payment value) {
            this.id = value.id;
            this.fromAccount = value.fromAccount;
            this.toAccount = value.toAccount;
            this.amount = value.amount;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        return id != null ? id.equals(payment.id) : payment.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
