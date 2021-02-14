package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.AuthenticationDto;

public interface AuthenticationService {

    String authorize(AuthenticationDto authenticationDto);
}
