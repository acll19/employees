package org.aca.studies.domain;


import java.math.BigDecimal;

public class Employee {

    private String name;
    private BigDecimal sallary;

    public Employee(String name, BigDecimal sallary) {
        this.name = name;
        this.sallary = sallary;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSallary() {
        return sallary;
    }

    public static class Builder {
        private String name;
        private BigDecimal sallary;

        public Employee build() {
            return new Employee(name, sallary);
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSallary(BigDecimal sallary) {
            this.sallary = sallary;
            return this;
        }
    }
}
