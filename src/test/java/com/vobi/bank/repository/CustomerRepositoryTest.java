package com.vobi.bank.repository;

import com.vobi.bank.entity.Customer;
import com.vobi.bank.entity.DocumentType;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Test
    @Order(1)
    void mustValidateDependencies() {
        assertNotNull(customerRepository);
        assertNotNull(documentTypeRepository);
    }

    @Test
    @Order(2)
    void mustCreateACustomer() {
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
        Customer savedCustomer = customerRepository.save(customer);

        //Assert
        assertNotNull(customer, "Customer was not saved :(");
    }

    @Test
    @Order(3)
    void mustModifyACustomer() {
        //Arrange
        Integer idCustomer = 14836554;

        Customer customer = customerRepository.findById(idCustomer).get();
        customer.setEnable("N");

        //Act
        Customer savedCustomer = customerRepository.save(customer);

        //Assert
        assertNotNull(customer, "Customer was not modified :(");
    }

    @Test
    @Order(4)
    void mustDeleteACustomer() {
        //Arrange
        Integer idCustomer = 14836554;

        Optional<Customer> customer = customerRepository.findById(idCustomer);
        assertTrue(customer.isPresent(), "Customer doesn't exists :(");

        //Act
        customerRepository.delete(customer.get());
        Optional<Customer> deletedCustomer = customerRepository.findById(idCustomer);

        //Assert
        assertFalse(deletedCustomer.isPresent(), "Customer was not deleted :(");
    }
}