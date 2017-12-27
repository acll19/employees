package org.aca.studies.infra.boundary;

import org.aca.studies.domain.entity.Employee;

import java.math.BigDecimal;

public class EmployeeResource {

    private String name;
    private BigDecimal salary;

    public EmployeeResource() {
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static EmployeeResource toResource(Employee employee) {
        EmployeeResource employeeResource = new EmployeeResource();
        employeeResource.setName(employee.getName());
        employeeResource.setSalary(employee.getSallary());
        return employeeResource;
    }

    public static Employee toEmployee(EmployeeResource employeeResource) {
        return new Employee(employeeResource.getName(), employeeResource.getSalary());
    }
}
