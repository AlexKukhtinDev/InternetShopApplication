package com.akukhtin.internetshop.service;

import com.akukhtin.internetshop.dto.UserDto;
import com.akukhtin.internetshop.exception.UserNotFoundException;
import com.akukhtin.internetshop.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id) throws UserNotFoundException;

    Optional<User> create(User user);

    Optional<User> update(Long userId, UserDto userDto);

    Optional<User> update(Long userId, User user);

    void delete(User user);
}
