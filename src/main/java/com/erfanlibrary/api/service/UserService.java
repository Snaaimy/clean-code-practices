package com.erfanlibrary.api.service;

import com.erfanlibrary.api.dto.user.UserResponseDto;
import com.erfanlibrary.api.exception.user.UserNotFoundException;
import com.erfanlibrary.api.interfaces.Validator;
import com.erfanlibrary.api.mapper.UserToUserResponseDto;
import com.erfanlibrary.api.model.User;
import com.erfanlibrary.api.repository.UserRepository;
import com.erfanlibrary.api.util.message.user.UserExceptionMessage;
import com.erfanlibrary.api.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private Validator<User> userValidator;

    @Autowired
    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

//    Chapter 6 violation number 1: exposing internal representation
    public User getUserByIdIncorrectWay(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(UserExceptionMessage.USER_NOT_FOUND)
        );
    }

    //    chapter 6 violation number 1: not exposing internal representation
    public UserResponseDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(UserExceptionMessage.USER_NOT_FOUND)
        );
        return UserToUserResponseDto.toCustomerDto(user);
    }

//    Chapter 6 number 2: Overly Coupled or “Train Wreck” Code
    public String getUserCityIncorrectWay(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        // Chaining multiple calls: a potential "train wreck"
        return user.getAddress().getCity().toLowerCase();
    }

    //    Chapter 6 number 2: not overly coupled or "train wreck" code
    public String getUserCity(Integer id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getCity();
    }


    public User addUser(User user) {
        userValidator.validate(user);
        return userRepository.save(user);
    }
}
