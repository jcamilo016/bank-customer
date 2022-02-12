package com.vobi.bank.repository;

import com.vobi.bank.entity.Customer;
import com.vobi.bank.entity.DocumentType;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Test
    void mustValidateDependencies() {
        assertNotNull(customerRepository);
        assertNotNull(documentTypeRepository);
    }

    @Test
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
}