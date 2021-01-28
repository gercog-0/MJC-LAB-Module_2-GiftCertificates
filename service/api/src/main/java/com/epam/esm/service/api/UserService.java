package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll(PaginationDto paginationDto);

    UserDto findById(Long id);
}
