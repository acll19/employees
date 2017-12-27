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
import java.util.ArrayList;
import java.util.List;

@Path("/employees")
public class EmployeeEndpoint {

    private EmployeeManager employeeManager;

    @Inject
    public void setEmployeeManager(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchEmployees() {
        List<Employee> employees = employeeManager.report();
        List<EmployeeResource> employeeResponse = new ArrayList<>();
        employees.stream()
                .map(EmployeeResource::toResource)
                .forEach(employeeResponse::add);
        return Response.ok(employeeResponse).build();
    }

    @POST
    public Response recruitEmployee(EmployeeResource employee) {
        Long newEmployeeId = employeeManager.recruit(EmployeeResource.toEmployee(employee));
        return Response.created(URI.create("/" + newEmployeeId)).build();
    }
}
