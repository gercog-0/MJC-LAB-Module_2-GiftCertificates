package com.epam.esm.controller;

import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> findAllUsers(@RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {
        PaginationDto paginationDto = new PaginationDto(page, size);
        return userService.findAll(paginationDto);
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable long id) {
        return userService.findById(id);
    }
}
