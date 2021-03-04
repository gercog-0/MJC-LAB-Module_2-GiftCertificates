package com.epam.esm.controller.security.factory;


import com.epam.esm.controller.security.entity.JwtUser;
import com.epam.esm.service.api.dto.FullUserDto;
import com.epam.esm.service.api.dto.RoleDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUserFactory {

    public JwtUser create(FullUserDto user) {
        return new JwtUser(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles())
        );
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(List<RoleDto> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
