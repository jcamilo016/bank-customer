package com.vobi.bank.repository;

import com.vobi.bank.entity.Customer;
import com.vobi.bank.entity.DocumentType;
import com.vobi.bank.entity.User;
import com.vobi.bank.entity.UserType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTypeRepository userTypeRepository;

    @Test
    @Order(1)
    void mustValidateDependencies() {
        assertNotNull(userRepository);
        assertNotNull(userTypeRepository);
    }

    @Test
    @Order(2)
    void mustCreateAnUser() {
        //Arrange
        UserType userType= userTypeRepository.findById(1).get();

        User user = new User();
        user.setUserEmail("jcamilo016@gmail.com");
        user.setName("Juan Camilo Acosta");
        user.setToken("JKH232342H");
        user.setEnable("Y");
        user.setUserType(userType);

        //Act
        User savedUser = userRepository.save(user);

        //Assert
        assertNotNull(user, "User was not saved :(");
    }

    @Test
    @Order(3)
    void mustModifyAnUser() {
        //Arrange
        String userEmail = "jcamilo016@gmail.com";

        User user = userRepository.findById(userEmail).get();
        user.setEnable("N");

        //Act
        User savedUser = userRepository.save(user);

        //Assert
        assertNotNull(savedUser, "User was not modified");
    }

    @Test
    @Order(4)
    void mustDeleteAnUser() {
        //Arrange
        String userEmail = "jcamilo016@gmail.com";

        Optional<User> user = userRepository.findById(userEmail);
        assertTrue(user.isPresent(), "User doesn't exists :(");

        //Act
        userRepository.delete(user.get());
        Optional<User> deletedUser = userRepository.findById(userEmail);

        //Assert
        assertFalse(deletedUser.isPresent(), "User was not deleted :(");
    }

    @Test
    @Order(5)
    void mustGetAllUsers() {
        //Arrange
        List<User> users = userRepository.findAll();
        assertFalse(users.isEmpty(), "User was not retrieved :(");

        users.forEach(user -> log.info(String.valueOf(user)));
    }
}