package org.aca.studies.domain;


public class Employee {

    private String name;

    public Employee() {
    }

    Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private String name;

        public Employee build() {
            return new Employee(name);
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
    }
}
