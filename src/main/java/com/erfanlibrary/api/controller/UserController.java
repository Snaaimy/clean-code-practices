package com.erfanlibrary.api.controller;


import com.erfanlibrary.api.dto.user.UserResponseDto;
import com.erfanlibrary.api.mapper.UserToUserResponseDto;
import com.erfanlibrary.api.model.User;
import com.erfanlibrary.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/com/erfanlibrary/api/v1/customer")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//   incorrect way, exposing the internal structure of the class
    @GetMapping("{id}")
    public ResponseEntity<User> getCustomerByIdIncorrectWay(@PathVariable Integer id) {

        User user = userService.getUserByIdIncorrectWay(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//   correct way,not exposing the internal structure of the class
    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getCustomerByIdFineWay(@PathVariable Integer id) {
        User user = userService.getUserByIdIncorrectWay(id);
        UserResponseDto customerDto = UserToUserResponseDto.toCustomerDto(user);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

//    even more correct way (not performing any business logic in the controller)
    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getCustomerById(@PathVariable Integer id) {

        UserResponseDto customer = userService.getUserById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
