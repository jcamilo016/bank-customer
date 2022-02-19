package com.vobi.bank.controller;

import com.vobi.bank.dto.CustomerDTO;
import com.vobi.bank.entity.Customer;
import com.vobi.bank.mapper.CustomerMapper;
import com.vobi.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll() throws Exception {
        List<CustomerDTO> customers = customerMapper.customerListToCustomerDTOList(customerService.findAll());
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable("id") Integer customerId) throws Exception {
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(
                customerService.findById(customerId).orElse(new Customer())
        );

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> save(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {
        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);
        customer = customerService.save(customer);

        return new ResponseEntity<>(customerMapper.customerToCustomerDTO(customer), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> put(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {
        Customer customer = customerMapper.customerDTOtoCustomer(customerDTO);
        customer = customerService.update(customer);

        return new ResponseEntity<>(customerMapper.customerToCustomerDTO(customer), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer customerId) throws Exception {
        customerService.deleteById(customerId);
    }
}
