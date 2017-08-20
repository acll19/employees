package org.aca.studies.domain.services;


import org.aca.studies.domain.Customer;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class CustomerServiceTest {

    @Test
    public void shouldReturnListWithCustomers() {
        CustomerService customerService = new CustomerService();

        List<Customer> customers = customerService.listCostomers();

        assertThat(customers).isNotEmpty();
    }

    @Test
    public void shouldREturnListWithCustomersWithNames() throws Exception {
        CustomerService customerService = new CustomerService();

        List<Customer> customers = customerService.listCostomers();

        customers.forEach(c -> assertThat(c.getName()).isNotNull());
        customers.forEach(c -> assertThat(c.getName()).isNotEmpty());
    }
}
