package org.aca.studies.infra.boundary;

import org.aca.studies.domain.entity.Employee;
import org.aca.studies.infra.control.EmployeeManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/employees")
public class EmployeeResource {

    private EmployeeManager employeeManager;

    @Inject
    public EmployeeResource(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    public EmployeeResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerEmployees() {
        List<Employee> employees = employeeManager.report();
        return Response.ok(employees).build();
    }

    @POST
    public Response recruitEmployee(Employee employee) {
        Long newEmployeeId = employeeManager.recruit(employee);
        return Response.created(URI.create("/" + newEmployeeId)).build();
    }
}
