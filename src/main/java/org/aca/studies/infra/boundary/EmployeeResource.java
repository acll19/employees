package org.aca.studies.infra.boundary;

import org.aca.studies.domain.Employee;
import org.aca.studies.domain.services.EmployeeService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
public class EmployeeResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerEmployees() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = employeeService.listCostomers();
        return Response.ok(employees).build();
    }
}
