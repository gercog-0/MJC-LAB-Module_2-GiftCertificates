package com.epam.esm.service.impl;

import com.epam.esm.dao.api.UserDao;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.service.api.UserService;
import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.dto.UserDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.BaseValidator;
import com.epam.esm.service.impl.validator.impl.PaginationDtoValidator;
import com.epam.esm.service.impl.validator.impl.UserValidatorImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.service.api.exception.ErrorCode.USER_WITH_SUCH_ID_NOT_EXIST;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final BaseValidator<UserDto> userValidator;
    private final BaseValidator<PaginationDto> paginationValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserValidatorImpl userValidator,
                           PaginationDtoValidator paginationValidator, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.userValidator = userValidator;
        this.paginationValidator = paginationValidator;
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
    public UserDto findById(Long id) {
        return userDao.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new ServiceException(USER_WITH_SUCH_ID_NOT_EXIST, String.valueOf(id)));
    }
}
