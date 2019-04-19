package info.ach.karate.gateway.service;

import info.ach.karate.gateway.model.Customer;

public interface ICustomerService {

    Customer getCustomer(String uuid);
}
