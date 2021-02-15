package com.epam.esm.controller.security.factory;

import com.epam.esm.dao.api.entity.Role;
import com.epam.esm.dao.api.entity.User;
import com.epam.esm.controller.security.entity.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUserFactory {

    public JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
               mapToGrantedAuthorities(user.getRoles())
        );
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
