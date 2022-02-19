package com.vobi.bank.service;

import com.vobi.bank.entity.User;
import com.vobi.bank.entity.UserType;
import com.vobi.bank.repository.UserTypeRepository;
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

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserTypeRepository userTypeRepository;

    @Test
    @Order(1)
    void mustValidateDependencies() {
        assertNotNull(userService);
    }

    @Test
    @Order(2)
    void mustCreateAnUser() throws Exception {
        //Arrange
        UserType userType= userTypeRepository.findById(1).get();

        User user = new User();
        user.setUserEmail("jcamilo016@gmail.com");
        user.setName("Juan Camilo Acosta");
        user.setToken("JKH232342H");
        user.setEnable("Y");
        user.setUserType(userType);

        //Act
        User savedUser = userService.save(user);

        //Assert
        assertNotNull(savedUser, "User was not saved :(");
    }

    @Test
    @Order(3)
    void mustModifyAnUser() throws Exception {
        //Arrange
        String userEmail = "jcamilo016@gmail.com";

        User user = userService.findById(userEmail).get();
        user.setEnable("N");

        //Act
        User savedUser = userService.update(user);

        //Assert
        assertNotNull(savedUser, "User was not modified");
    }

    @Test
    @Order(4)
    void mustDeleteAnUser() throws Exception {
        //Arrange
        String userEmail = "jcamilo016@gmail.com";

        Optional<User> user = userService.findById(userEmail);
        assertTrue(user.isPresent(), "User doesn't exists :(");

        //Act
        userService.delete(user.get());
        Optional<User> deletedUser = userService.findById(userEmail);

        //Assert
        assertFalse(deletedUser.isPresent(), "User was not deleted :(");
    }

    @Test
    @Order(5)
    void mustGetAllUsers() {
        //Arrange
        List<User> customers = userService.findAll();
        assertFalse(customers.isEmpty(), "Users was not retrieved :(");

        customers.forEach(customer -> log.info(String.valueOf(customer)));
    }
}
