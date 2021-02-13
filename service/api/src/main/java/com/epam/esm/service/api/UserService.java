package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.dto.UserDto;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService extends BaseService<UserDto>{

    /**
     * Find all list.
     *
     * @param paginationDto the pagination dto
     * @return the list
     */
    List<UserDto> findAll(PaginationDto paginationDto);
}
