package org.aca.studies.domain;


public class Customer {

    private String name;

    public Customer() {
    }

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private String name;

        public Customer build() {
            return new Customer(name);
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
    }
}
