package com.epam.esm.controller;

import com.epam.esm.service.api.AuthenticationService;
import com.epam.esm.service.api.dto.AuthenticationDto;
import com.epam.esm.service.api.dto.AuthenticationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authorize")
    public AuthenticationResponseDto login(@RequestBody AuthenticationDto authenticationDto) {
        String currentToken = authenticationService.authorize(authenticationDto);
        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();
        authenticationResponseDto.setLogin(authenticationDto.getLogin());
        authenticationResponseDto.setToken(currentToken);
        return authenticationResponseDto;
    }

}
