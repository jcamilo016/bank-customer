package com.vobi.bank.service;

import com.vobi.bank.entity.Customer;
import com.vobi.bank.entity.DocumentType;
import com.vobi.bank.repository.DocumentTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Test
    @Order(1)
    void mustValidateDependencies() {
        assertNotNull(customerService);
    }

    @Test
    @Order(2)
    void mustCreateACustomer() throws Exception {
        //Arrange
        Integer idDocumentType = 1;
        Integer idCustomer = 14836554;


        DocumentType documentType = documentTypeRepository.findById(idDocumentType).get();
        Customer customer = new Customer();
        customer.setCustId(idCustomer);
        customer.setDocumentType(documentType);
        customer.setAddress("Calle 7 # 83-31");
        customer.setEnable("Y");
        customer.setPhone("3168684548");
        customer.setEmail("jcamilo016@gmail.com");
        customer.setName("Juan Camilo Acosta Escobar");
        customer.setToken("asdasdajfsfpwoejlksa");

        //Act
        Customer savedCustomer = customerService.save(customer);

        //Assert
        assertNotNull(savedCustomer, "Customer was not saved :(");
    }

    @Test
    @Order(3)
    void mustModifyACustomer() throws Exception {
        //Arrange
        Integer idCustomer = 14836554;

        Optional<Customer> customer = customerService.findById(idCustomer);

        assertTrue(customer.isPresent(), "Customer doesn't exists :(");
        customer.get().setEnable("N");

        //Act
        Customer savedCustomer = customerService.update(customer.get());

        //Assert
        assertNotNull(savedCustomer, "Customer was not modified :(");
    }

    @Test
    @Order(4)
    void mustDeleteACustomer() throws Exception {
        //Arrange
        Integer idCustomer = 14836554;

        Optional<Customer> customer = customerService.findById(idCustomer);
        assertTrue(customer.isPresent(), "Customer doesn't exists :(");

        //Act
        customerService.delete(customer.get());
        Optional<Customer> deletedCustomer = customerService.findById(idCustomer);

        //Assert
        assertFalse(deletedCustomer.isPresent(), "Customer was not deleted :(");
    }

    @Test
    @Order(5)
    void mustGetAllCustomers() {
        //Arrange
        List<Customer> customers = customerService.findAll();
        assertFalse(customers.isEmpty(), "Customers was not retrieved :(");

        customers.forEach(customer -> log.info(String.valueOf(customer)));
    }
}
