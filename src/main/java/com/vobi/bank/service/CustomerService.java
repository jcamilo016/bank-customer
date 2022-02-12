package com.vobi.bank.service;

import com.vobi.bank.entity.Account;
import com.vobi.bank.entity.Customer;
import com.vobi.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomer(int customerId)  {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.orElseThrow();
    }

    public List<Customer> getCustomersByAddress(String address) {
        return customerRepository.findCustomerByAddress(address);
    }

}
