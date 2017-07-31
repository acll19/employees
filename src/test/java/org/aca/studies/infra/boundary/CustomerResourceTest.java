package org.aca.studies.infra.boundary;

import org.aca.studies.domain.Customer;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerResourceTest {

    @Test
    public void obtenerCustomersShouldReturn() throws Exception {
        CustomerResource customerResource = new CustomerResource();

        Response response = customerResource.obtenerCustomers();

        List<Customer> customers = (List<Customer>) response.getEntity();

        assertThat(customers).isNotEmpty();
    }

}