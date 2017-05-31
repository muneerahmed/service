package com.learn.service.controller;

import com.learn.service.resource.Customer;
import com.learn.service.resource.Name;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/customers", produces = "application/hal+json")
@ExposesResourceFor(Customer.class)
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class CustomerController {

    private final
    @NonNull
    EntityLinks entityLinks;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<Customer>> getCustomers() {
        Resources<Customer> resources = new Resources<>(getCustomersList());
        resources.add(entityLinks.linkToCollectionResource(Customer.class));
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<Resource<Customer>> getCustomer(@PathVariable Long id) {
        Resource<Customer> resource = new Resource<>(getCustomer());
        resource.add(entityLinks.linkToSingleResource(Customer.class, id));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    private List<Customer> getCustomersList() {
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(getCustomer());
        return customers;
    }

    private Customer getCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName(new Name());
        customer.getName().setFirstName("Omar");
        customer.getName().setMiddleName("Ibn");
        customer.getName().setLastName("Al-Khattab");
        return customer;
    }

}
