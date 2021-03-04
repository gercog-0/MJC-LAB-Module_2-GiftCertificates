package com.epam.esm.controller.security.service;

import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.FullUserDto;
import com.epam.esm.controller.security.factory.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final JwtUserFactory jwtUserFactory;

    @Autowired
    public JwtUserDetailsService(UserService userService, JwtUserFactory jwtUserFactory) {
        this.userService = userService;
        this.jwtUserFactory = jwtUserFactory;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        FullUserDto fullUserDto = userService.findByLogin(username);
        return jwtUserFactory.create(fullUserDto);
    }
}
