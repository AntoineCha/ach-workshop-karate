package info.ach.karate.referentiel.service;

import info.ach.karate.referentiel.model.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> listAll();

    Customer get(String uuid);

}
