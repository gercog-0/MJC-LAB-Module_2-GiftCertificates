package com.epam.esm.service.impl;

import com.epam.esm.dao.api.UserDao;
import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.UserDto;
import com.epam.esm.service.impl.validator.BaseValidator;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    private UserDao userDao;
    @MockBean
    private BaseValidator<UserDto> validator;
    private UserService userService;

    @Autowired
    public UserServiceImplTest(UserService userService) {
        this.userService = userService;
    }
}
