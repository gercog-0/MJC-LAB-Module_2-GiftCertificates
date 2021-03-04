package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.AuthenticationDto;
import com.epam.esm.service.api.dto.FullUserDto;
import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.dto.UserDto;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Find all list.
     *
     * @param paginationDto the pagination dto
     * @return the list
     */
    List<UserDto> findAll(PaginationDto paginationDto);

    /**
     * Find by id user dto.
     *
     * @param id the id
     * @return the user dto
     */
    UserDto findById(long id);

    /**
     * Register user dto.
     *
     * @param fullUserDto the full user dto
     * @return the user dto
     */
    UserDto register(FullUserDto fullUserDto);

    /**
     * Remove.
     *
     * @param id the id
     */
    void remove(long id);

    /**
     * Find by login user dto.
     *
     * @param login the login
     * @return the full user dto
     */
    FullUserDto findByLogin(String login);

    /**
     * Authorize user dto.
     *
     * @param authenticationDto the authentication dto
     * @return the user dto
     */
    UserDto authorize(AuthenticationDto authenticationDto);
}
