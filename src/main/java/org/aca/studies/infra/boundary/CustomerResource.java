package org.aca.studies.infra.boundary;

import org.aca.studies.domain.Customer;
import org.aca.studies.services.CustomerService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
public class CustomerResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerCustomers() {
        CustomerService customerService = new CustomerService();
        List<Customer> customers = customerService.listCostomers();
        return Response.ok(customers).build();
    }
}
