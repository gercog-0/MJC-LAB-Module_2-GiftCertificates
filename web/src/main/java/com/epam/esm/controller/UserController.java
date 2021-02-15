package com.epam.esm.controller;

import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        List<UserDto> usersDto = userService.findAll(paginationDto);
        usersDto.forEach(this::addDependenciesLinks);
        return usersDto;
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable long id) {
        UserDto userDto = userService.findById(id);
        addDependenciesLinks(userDto);
        return userDto;
    }

    @PostMapping(path = "/registration")
    public UserDto register(@RequestBody UserDto userDto){
        UserDto registeredUser = userService.add(userDto);
        addDependenciesLinks(registeredUser);
        return registeredUser;
    }

    private void addDependenciesLinks(UserDto userDto) {
        userDto.add(linkTo(methodOn(UserController.class).findUserById(userDto.getId())).withSelfRel());
    }
}
