package org.aca.studies.domain.services;


import org.aca.studies.domain.Employee;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class EmployeeServiceTest {

    @Test
    public void shouldReturnListWithEmployees() {
        EmployeeService employeeService = new EmployeeService();

        List<Employee> Employees = employeeService.listCostomers();

        assertThat(Employees).isNotEmpty();
    }

    @Test
    public void shouldREturnListWithEmployeesWithNames() throws Exception {
        EmployeeService employeeService = new EmployeeService();

        List<Employee> Employees = employeeService.listCostomers();

        Employees.forEach(c -> assertThat(c.getName()).isNotNull());
        Employees.forEach(c -> assertThat(c.getName()).isNotEmpty());
    }
}
