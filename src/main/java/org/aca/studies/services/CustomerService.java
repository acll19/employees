package org.aca.studies.services;

import org.aca.studies.domain.Customer;

import java.util.Arrays;
import java.util.List;

public class CustomerService {

    public List<Customer> listCostomers() {
        return Arrays.asList(
                new Customer.Builder()
                .withName("Customer A")
                .build(),
                new Customer.Builder()
                .withName("Customer B")
                .build()
        );
    }
}
