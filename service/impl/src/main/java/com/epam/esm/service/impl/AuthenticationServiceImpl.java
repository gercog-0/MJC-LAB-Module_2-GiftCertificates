package com.epam.esm.service.impl;

import com.epam.esm.service.api.AuthenticationService;
import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.AuthenticationDto;
import com.epam.esm.service.api.dto.UserDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.security.provider.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import static com.epam.esm.service.api.exception.ErrorCode.INCORRECT_LOGIN_OR_PASSWORD;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtTokenProvider provider;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(JwtTokenProvider provider, UserService userService, AuthenticationManager authenticationManager) {
        this.provider = provider;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String authorize(AuthenticationDto authenticationDto) {
        String login = authenticationDto.getLogin();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login,
                    authenticationDto.getPassword()));
            UserDto userDto = userService.findByLogin(login);
            return provider.create(login, userDto.getRoles());
        } catch (AuthenticationException exp) {
            throw new ServiceException(INCORRECT_LOGIN_OR_PASSWORD);
        }
    }
}
