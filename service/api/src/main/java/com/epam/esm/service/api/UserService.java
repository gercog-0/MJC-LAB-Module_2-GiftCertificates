package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    UserDto findById(Long id);
}
