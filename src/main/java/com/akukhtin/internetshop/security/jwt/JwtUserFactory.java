package com.akukhtin.internetshop.security.jwt;

import com.akukhtin.internetshop.model.Role;
import com.akukhtin.internetshop.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(user.getId(),
                user.getUsername(), user.getFirstName(),
                user.getLastName(), user.getPassword(),
                user.getEmail(), user.getStatus().equals("ACTIVE"),
                user.getUpdated(), mapToGrantedAuthority(new ArrayList<>(user.getRoles())));
    }
    private static List<GrantedAuthority> mapToGrantedAuthority(List<Role> userRoles){
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
