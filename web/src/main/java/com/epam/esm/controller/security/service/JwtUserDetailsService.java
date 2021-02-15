package com.epam.esm.controller.security.service;

import com.epam.esm.dao.api.entity.User;
import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.UserDto;
import com.epam.esm.controller.security.factory.JwtUserFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final JwtUserFactory jwtUserFactory;
    private final ModelMapper modelMapper;

    @Autowired
    public JwtUserDetailsService(UserService userService, JwtUserFactory jwtUserFactory, ModelMapper modelMapper) {
        this.userService = userService;
        this.jwtUserFactory = jwtUserFactory;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = userService.findByLogin(username);
        User user = modelMapper.map(userDto, User.class);
        return jwtUserFactory.create(user);
    }
}
