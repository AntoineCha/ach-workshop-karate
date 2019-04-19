package info.ach.karate.referentiel.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.ach.karate.referentiel.model.Customer;

import java.util.List;

@JsonAutoDetect
public class CustomersDto {

    @JsonProperty("customers")
    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
