package org.aca.studies.infra.boundary;

import org.aca.studies.UnitTest;
import org.aca.studies.domain.entity.Employee;
import org.aca.studies.infra.control.EmployeeManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Category(UnitTest.class)
@RunWith(MockitoJUnitRunner.class)
public class EmployeeResourceTest {

    @Mock
    private EmployeeManager employeeManager;
    private EmployeeResource employeeResource;

    @Before
    public void setUp() {
        employeeResource = new EmployeeResource(employeeManager);
    }

    @Test
    public void obtenerEmployeesShouldReturnAListOfEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee("Armando", BigDecimal.valueOf(1500.0)),
                new Employee("Yamile", BigDecimal.valueOf(1800.5))
        );
        when(employeeManager.report()).thenReturn(employees);

        Response response = employeeResource.obtenerEmployees();
        List<Employee> employeesResponse = (List<Employee>) response.getEntity();

        verify(employeeManager).report();
        assertThat(employeesResponse).isNotEmpty();
    }

    @Test
    public void recruitEmployeeShouldCallRecruitOnEmployeeManager() {
        String name = "Armando";
        BigDecimal salary = BigDecimal.valueOf(1500.0);
        Employee employee = new Employee(name, salary);

        when(employeeManager.recruit(employee)).thenReturn(1L);

        Response response = employeeResource.recruitEmployee(employee);

        verify(employeeManager).recruit(any(Employee.class));
        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getLocation().toString()).contains("/1");
    }
}