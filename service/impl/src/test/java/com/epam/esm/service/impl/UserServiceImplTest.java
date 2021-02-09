package com.epam.esm.service.impl;

import com.epam.esm.dao.api.UserDao;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.dao.api.entity.User;
import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.PaginationDto;

import com.epam.esm.service.api.dto.UserDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.impl.PaginationDtoValidator;
import com.epam.esm.service.impl.validator.impl.UserValidatorImpl;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Arrays;
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

    @BeforeEach
    void setUp() {
        userDao = mock(UserDao.class);
        validator = mock(UserValidatorImpl.class);
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        userService = new UserServiceImpl(userDao, validator, new PaginationDtoValidator(), modelMapper);
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
        when(userDao.findAll(any(Pagination.class))).thenReturn(Arrays.asList(new User(1L, "Ivan"), new User(2L, "Oleg")));
        int expectedSize = 2;

        int actualSize = userService.findAll(new PaginationDto(1, 5)).size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void methodFindByIdShouldReturnUserCorrect() {
        UserDto expectedUser = new UserDto(1L, "Ivan");
        Optional<User> returnUser = Optional.of(new User(1L, "Ivan"));
        when(userDao.findById(any(Long.class))).thenReturn(returnUser);

        UserDto actualOptionalUser = userService.findById(1L);

        assertEquals(actualOptionalUser, expectedUser);
    }

    @Test
    void methodFindByIdShouldRThrowException() {
        when(userDao.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> userService.findById(1L));
    }
}
