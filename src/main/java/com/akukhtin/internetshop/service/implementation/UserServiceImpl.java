package com.akukhtin.internetshop.service.implementation;

import com.akukhtin.internetshop.dto.UserDto;
import com.akukhtin.internetshop.exception.UserNotFoundException;
import com.akukhtin.internetshop.model.Role;
import com.akukhtin.internetshop.model.Status;
import com.akukhtin.internetshop.model.User;
import com.akukhtin.internetshop.repository.RoleRepository;
import com.akukhtin.internetshop.repository.UserRepository;
import com.akukhtin.internetshop.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            throw new UserNotFoundException("User with id " + id + "Not Found");
        }
        return result;
    }

    @Transactional
    @Override
    public Optional<User> create(User user) {
        User saveUser = userRepository.save(user);
        return Optional.of(saveUser);
    }

    @Transactional
    @Override
    public Optional<User> update(Long userId, UserDto userDto) {
        User newUser = userRepository.findById(userId).orElseThrow();
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        return Optional.of(newUser);
    }

    @Override
    public Optional<User> update(Long userId, User user) {
        user.setId(userId);
        userRepository.saveAndFlush(user);
        return Optional.of(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
