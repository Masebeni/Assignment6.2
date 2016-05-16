package com.mobile.domain.bank;

import java.io.Serializable;

/**
 * Created by Isiphile on 2016-04-20.
 */
public class Bank implements Serializable {
    private Long id;
    private String name;
    private String branch;

    private Bank() {

    }

    public Long getId() {return id; }
    public String getName() {return name;}
    public String getBranch() {return branch; }


    public Bank(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.branch = builder.branch;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String branch;

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder branch(String value) {
            this.branch = value;
            return this;
        }


        public Builder copy(Bank value) {
            this.id = value.id;
            this.name = value.name;
            this.branch = value.branch;
            return this;
        }

        public Bank build() {
            return new Bank(this);

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        return id != null ? id.equals(bank.id) : bank.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
