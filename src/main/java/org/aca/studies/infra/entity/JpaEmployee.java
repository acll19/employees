package org.aca.studies.infra.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class JpaEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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

    public Long getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaEmployee that = (JpaEmployee) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
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
