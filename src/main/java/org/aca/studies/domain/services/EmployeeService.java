package org.aca.studies.domain.services;

import org.aca.studies.domain.entity.Employee;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class EmployeeService {

    public List<Employee> listEmployees() {
        return Arrays.asList(
                new Employee.Builder()
                        .withName("Employee A")
                        .withSallary(BigDecimal.valueOf(1500.0))
                        .build(),
                new Employee.Builder()
                        .withName("Employee B")
                        .withSallary(BigDecimal.valueOf(1400.0))
                        .build()
        );
    }
}
