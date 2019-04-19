package info.ach.karate.referentiel.controller;

import info.ach.karate.referentiel.dto.JsonResponseDto;
import info.ach.karate.referentiel.model.Customer;
import info.ach.karate.referentiel.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/referentiel/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public JsonResponseDto<List<Customer>> list() {
        return new JsonResponseDto(customerService.listAll());
    }

    @GetMapping("/{uuid}")
    public JsonResponseDto<Customer> get(@PathVariable String uuid) {
        return new JsonResponseDto(customerService.get(uuid));
    }


}
