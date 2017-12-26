package org.aca.studies.domain.services;


import org.aca.studies.UnitTest;
import org.aca.studies.domain.entity.Employee;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Category(UnitTest.class)
public class EmployeeServiceTest {

    @Test
    public void shouldReturnListWithEmployees() {
        EmployeeService employeeService = new EmployeeService();

        List<Employee> Employees = employeeService.listEmployees();

        assertThat(Employees).isNotEmpty();
    }

    @Test
    public void shouldReturnListWithEmployeesWithNamesAndSallary() throws Exception {
        EmployeeService employeeService = new EmployeeService();

        List<Employee> Employees = employeeService.listEmployees();

        Employees.forEach(c -> assertThat(c.getName()).isNotNull());
        Employees.forEach(c -> assertThat(c.getName()).isNotEmpty());
        Employees.forEach(c -> assertThat(c.getSallary()).isNotNull());
    }
}
