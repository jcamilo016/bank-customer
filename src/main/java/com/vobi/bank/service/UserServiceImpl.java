package com.vobi.bank.service;

import com.vobi.bank.entity.User;
import com.vobi.bank.repository.UserRepository;
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
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Validator validator;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(String userEmail) {
        return userRepository.findById(userEmail);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public User save(User entity) throws Exception {
        if (Objects.isNull(entity)) {
            throw new Exception("The user entity is null");
        }

        validate(entity);

        if (userRepository.existsById(entity.getUserEmail())) {
            throw new Exception("User already exists");
        }

        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) throws Exception {
        if (Objects.isNull(entity)) {
            throw new Exception("The user entity is null");
        }

        validate(entity);

        if (!userRepository.existsById(entity.getUserEmail())) {
            throw new Exception("Customer doesn't exists!");
        }

        return userRepository.save(entity);
    }

    @Override
    public void delete(User entity) throws Exception {
        if (Objects.isNull(entity)) {
            throw new Exception("The user entity is null");
        }

        if (Objects.isNull(entity.getUserEmail())) {
            throw new Exception("The user entity doesn't have an Id");
        }

        userRepository.delete(entity);
    }

    @Override
    public void deleteById(String userEmail) throws Exception {
        if (userEmail == null || userEmail.equals("")) {
            throw new Exception("Customer Id provided is invalid");
        }

        userRepository.deleteById(userEmail);
    }

    @Override
    public void validate(User entity) {
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(entity);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return userRepository.count();
    }
}
