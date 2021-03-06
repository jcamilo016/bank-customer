package com.vobi.bank.service;

import com.vobi.bank.entity.Customer;
import com.vobi.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    Validator validator;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findById(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return customerRepository.count();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Customer save(Customer customer) throws Exception {
        if (Objects.isNull(customer)) {
            throw new Exception("The customer entity is null");
        }

        validate(customer);

        if (customerRepository.existsById(customer.getCustId())) {
            throw new Exception("Customer already exists");
        }

        return customerRepository.save(customer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Customer update(Customer customer) throws Exception {
        if (Objects.isNull(customer)) {
            throw new Exception("The customer entity is null");
        }

        validate(customer);

        if (!customerRepository.existsById(customer.getCustId())) {
            throw new Exception("Customer doesn't exists!");
        }

        return customerRepository.save(customer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Customer entity) throws Exception {
        if (entity == null) {
            throw new Exception("The customer is null");
        }

        if (entity.getCustId() == null) {
            throw new Exception("The customer is null");
        }

        if (!customerRepository.existsById(entity.getCustId())) {
            throw new Exception("The customer doesn't exist");
        }

        findById(entity.getCustId()).ifPresent(customer -> {
            if (customer.getAccounts() != null && !customer.getAccounts().isEmpty()) {
                throw new RuntimeException("The customer has associated accounts");
            }

            if (customer.getRegisteredAccounts() != null && !customer.getRegisteredAccounts().isEmpty()) {
                throw new RuntimeException("The customer has associated registered accounts");
            }
        });

        customerRepository.deleteById(entity.getCustId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteById(Integer customerId) throws Exception {
        if (customerId == null)
            throw new Exception("The customer id is null");

        if (!customerRepository.existsById(customerId)) {
            throw new Exception("The customer does not exist");
        }

        delete(customerRepository.findById(customerId).get());
    }

    @Override
    public void validate(Customer entity) throws ConstraintViolationException {
        Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(entity);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
