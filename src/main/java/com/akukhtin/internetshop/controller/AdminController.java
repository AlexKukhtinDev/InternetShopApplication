package com.akukhtin.internetshop.controller;

import com.akukhtin.internetshop.dto.UserDto;
import com.akukhtin.internetshop.exception.UserNotFoundException;
import com.akukhtin.internetshop.model.User;
import com.akukhtin.internetshop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDto> getUserById (@PathVariable(name = "id") Long id)
            throws UserNotFoundException {
        User user = userService.findById(id);
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
