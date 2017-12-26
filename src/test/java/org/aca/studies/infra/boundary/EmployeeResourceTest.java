package org.aca.studies.infra.boundary;

import org.aca.studies.UnitTest;
import org.aca.studies.domain.Employee;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Category(UnitTest.class)
public class EmployeeResourceTest {

    @Test
    public void obtenerEmployeesShouldReturn() throws Exception {
        EmployeeResource employeeResource = new EmployeeResource();

        Response response = employeeResource.obtenerEmployees();

        List<Employee> Employees = (List<Employee>) response.getEntity();

        assertThat(Employees).isNotEmpty();
    }

}