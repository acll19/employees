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
public class EmployeeEndpointTest {

    @Mock
    private EmployeeManager employeeManager;
    private EmployeeEndpoint employeeEndpoint;

    @Before
    public void setUp() {
        employeeEndpoint = new EmployeeEndpoint(employeeManager);
    }

    @Test
    public void obtenerEmployeesShouldReturnAListOfEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee("Armando", BigDecimal.valueOf(1500.0)),
                new Employee("Yamile", BigDecimal.valueOf(1800.5))
        );
        when(employeeManager.report()).thenReturn(employees);

        Response response = employeeEndpoint.fetchEmployees();
        List<EmployeeResource> employeesResponse = (List<EmployeeResource>) response.getEntity();

        verify(employeeManager).report();
        assertThat(employeesResponse).isNotEmpty();
        employeesResponse.forEach(em -> assertThat(em).isInstanceOf(EmployeeResource.class));
    }

    @Test
    public void recruitEmployeeShouldCallRecruitOnEmployeeManager() {
        String name = "Armando";
        BigDecimal salary = BigDecimal.valueOf(1500.0);

        EmployeeResource employeeResource = new EmployeeResource();
        employeeResource.setName(name);
        employeeResource.setSalary(salary);

        when(employeeManager.recruit(any(Employee.class))).thenReturn(1L);

        Response response = employeeEndpoint.recruitEmployee(employeeResource);

        verify(employeeManager).recruit(any(Employee.class));
        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getLocation().toString()).contains("/1");
    }
}