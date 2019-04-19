package info.ach.karate.referentiel.service.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.ach.karate.referentiel.dto.CustomersDto;
import info.ach.karate.referentiel.model.Customer;
import info.ach.karate.referentiel.service.ICustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JsonCustomerService implements ICustomerService {

    private static ObjectMapper mapper = new ObjectMapper();

    private static Map<String, Customer> customers;

    static {
        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    }

    @PostConstruct
    public void init() {
        if(customers == null) {
            try {
                CustomersDto customersDto = mapper.readValue(getClass().getClassLoader().getResource("customers.json"),  CustomersDto.class);
                this.customers = customersDto.getCustomers().stream().collect(Collectors.toMap(Customer::getUuid, c -> c));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Customer> listAll() {
        return customers.values().stream().collect(Collectors.toList());
    }

    public Customer get(String uuid) {
        return customers.get(uuid);
    }

}
