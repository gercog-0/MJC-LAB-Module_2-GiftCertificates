package com.epam.esm.controller;

import com.epam.esm.controller.security.provider.JwtTokenProvider;
import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.AuthenticationDto;
import com.epam.esm.controller.security.entity.AuthenticationResponse;
import com.epam.esm.service.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/authentication")
public class AuthenticationController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthenticationController(UserService userService, JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/authorize")
    public AuthenticationResponse login(@RequestBody AuthenticationDto authenticationDto) {
        UserDto userDto = userService.authorize(authenticationDto);
        String userLogin = userDto.getLogin();
        String currentToken = tokenProvider.create(userLogin);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setLogin(userLogin);
        authenticationResponse.setToken(currentToken);
        return authenticationResponse;
    }
}
