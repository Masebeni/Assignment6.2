package com.mobile.domain.bank;

import java.io.Serializable;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class BankContactDetails implements Serializable {
    private Long id;
    private String phoneNumber;
    private String email;

    private BankContactDetails(){}

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BankContactDetails(Builder builder) {
        this.id = builder.id;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
    }

    public static class Builder{
        private Long id;
        private String phoneNumber;
        private String email;

        public Builder id(Long value){
            this.id =value;
            return this;
        }

        public Builder phoneNumber(String value){
            this.phoneNumber =value;
            return this;
        }

        public Builder email(String value){
            this.email =value;
            return this;
        }

        public Builder copy(BankContactDetails value){
            this.id = value.id;
            this.phoneNumber = value.phoneNumber;
            this.email=value.email;
            return  this;
        }

        public BankContactDetails build(){
            return new BankContactDetails(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankContactDetails bankContactDetails = (BankContactDetails) o;

        return id.equals(bankContactDetails.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
