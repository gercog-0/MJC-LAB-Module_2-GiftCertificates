package com.epam.esm.service.impl;

import com.epam.esm.dao.api.UserDao;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.dao.api.entity.User;
import com.epam.esm.service.api.RoleService;
import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.PaginationDto;

import com.epam.esm.service.api.dto.UserDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.impl.PaginationDtoValidator;
import com.epam.esm.service.impl.validator.impl.UserValidatorImpl;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private UserDao userDao;
    private UserValidatorImpl validator;
    private ModelMapper modelMapper;
    private UserService userService;
    private RoleService roleService;
    private BCryptPasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        userDao = mock(UserDao.class);
        validator = mock(UserValidatorImpl.class);
        roleService = mock(RoleServiceImpl.class);
        encoder = mock(BCryptPasswordEncoder.class);
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        userService = new UserServiceImpl(userDao, roleService, validator, new PaginationDtoValidator(), encoder,modelMapper);
    }

    @AfterEach
    void tearDown() {
        userDao = null;
        validator = null;
        modelMapper = null;
        userService = null;
    }

    @Test
    void methodFindAllShouldReturnCorrectSizeUsers() {
        when(userDao.findAll(any(Pagination.class))).thenReturn(Arrays.asList(new User(1L, "Ivan", "gercog", "12345", Collections.EMPTY_LIST),
                new User(2L, "Oleg", "olezha12", "12345", Collections.EMPTY_LIST)));
        int expectedSize = 2;

        int actualSize = userService.findAll(new PaginationDto(1, 5)).size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void methodFindByIdShouldReturnUserCorrect() {
        UserDto expectedUser = new UserDto(1L, "Ivan", "gercog");
        Optional<User> returnUser = Optional.of(new User(1L, "Ivan", "gercog", "12345", Collections.EMPTY_LIST));
        when(userDao.findById(any(Long.class))).thenReturn(returnUser);

        UserDto actualOptionalUser = userService.findById(1L);

        assertEquals(actualOptionalUser, expectedUser);
    }

    @Test
    void methodFindByIdShouldThrowException() {
        when(userDao.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> userService.findById(1L));
    }
}
