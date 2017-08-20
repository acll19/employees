package org.aca.studies.domain.services;

import org.aca.studies.domain.Employee;

import java.util.Arrays;
import java.util.List;

public class EmployeeService {

    public List<Employee> listCostomers() {
        return Arrays.asList(
                new Employee.Builder()
                .withName("Employee A")
                .build(),
                new Employee.Builder()
                .withName("Employee B")
                .build()
        );
    }
}
