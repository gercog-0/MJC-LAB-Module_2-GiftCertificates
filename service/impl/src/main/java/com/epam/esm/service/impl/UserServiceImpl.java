package com.epam.esm.service.impl;

import com.epam.esm.dao.api.UserDao;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.dao.api.entity.User;
import com.epam.esm.service.api.RoleService;
import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.AuthenticationDto;
import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.dto.RoleDto;
import com.epam.esm.service.api.dto.UserDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.BaseValidator;
import com.epam.esm.service.impl.validator.impl.PaginationDtoValidator;
import com.epam.esm.service.impl.validator.impl.UserValidatorImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.esm.service.api.exception.ErrorCode.INCORRECT_LOGIN_OR_PASSWORD;
import static com.epam.esm.service.api.exception.ErrorCode.USER_WITH_SUCH_ID_NOT_EXIST;
import static com.epam.esm.service.api.exception.ErrorCode.USER_WITH_SUCH_LOGIN_ALREADY_EXIST;
import static com.epam.esm.service.api.exception.ErrorCode.USER_WITH_SUCH_LOGIN_NOT_EXIST;

@Service
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_USER_ROLE = "USER_ROLE";

    private final UserDao userDao;
    private final RoleService roleService;
    private final BaseValidator<UserDto> userValidator;
    private final BaseValidator<PaginationDto> paginationValidator;
    private final BCryptPasswordEncoder encoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleService roleService, UserValidatorImpl userValidator,
                           PaginationDtoValidator paginationValidator, BCryptPasswordEncoder encoder, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.userValidator = userValidator;
        this.paginationValidator = paginationValidator;
        this.encoder = encoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> findAll(PaginationDto paginationDto) {
        paginationValidator.validate(paginationDto);
        Pagination pagination = modelMapper.map(paginationDto, Pagination.class);
        return userDao.findAll(pagination).stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findByLogin(String login) {
        Optional<User> foundUser = userDao.findByLogin(login);
        if (!foundUser.isPresent()) {
            throw new ServiceException(USER_WITH_SUCH_LOGIN_NOT_EXIST, login);
        }
        return modelMapper.map(foundUser.get(), UserDto.class);
    }

    @Transactional
    @Override
    public UserDto authorize(AuthenticationDto authenticationDto) {
        String userLogin = authenticationDto.getLogin();
        Optional<User> foundUser = userDao.findByLogin(userLogin);
        if (!foundUser.isPresent() || !encoder.matches(foundUser.get().getPassword(), authenticationDto.getPassword())) {
            throw new ServiceException(INCORRECT_LOGIN_OR_PASSWORD);
        }
        return modelMapper.map(foundUser, UserDto.class);
    }

    @Override
    public UserDto findById(long id) {
        return userDao.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new ServiceException(USER_WITH_SUCH_ID_NOT_EXIST, String.valueOf(id)));
    }

    @Transactional
    @Override
    public UserDto add(UserDto userDto) {
        userValidator.validate(userDto);
        checkLoginToUnique(userDto.getLogin());
        RoleDto defaultUserRole = roleService.findByName(DEFAULT_USER_ROLE);
        userDto.setRoles(Collections.singletonList(defaultUserRole));
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user = modelMapper.map(userDto, User.class);
        User addedUser = userDao.add(user);
        return modelMapper.map(addedUser, UserDto.class);
    }

    @Transactional
    @Override
    public void remove(long id) {
        findById(id);
        userDao.remove(id);
    }

    private void checkLoginToUnique(String login) {
        boolean isExist = userDao.findByLogin(login).isPresent();
        if (isExist) {
            throw new ServiceException(USER_WITH_SUCH_LOGIN_ALREADY_EXIST, login);
        }
    }
}
