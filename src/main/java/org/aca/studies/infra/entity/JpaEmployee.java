package org.aca.studies.infra.entity;

import java.math.BigDecimal;

public class JpaEmployee {

    private String name;
    private BigDecimal sallary;

    //JPA Requires this
    protected JpaEmployee() {}

    private JpaEmployee(String name, BigDecimal sallary) {
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

        public Builder withName(String aName) {
            this.name = aName;
            return this;
        }

        public Builder withSallary(BigDecimal aSallary) {
            this.sallary = aSallary;
            return this;
        }

        public JpaEmployee build() {
            return new JpaEmployee(name, sallary);
        }
    }
}
