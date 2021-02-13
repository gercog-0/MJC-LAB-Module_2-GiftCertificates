package com.epam.esm.service.impl;

import com.epam.esm.dao.api.RoleDao;
import com.epam.esm.dao.api.entity.Role;
import com.epam.esm.service.api.RoleService;
import com.epam.esm.service.api.dto.RoleDto;
import com.epam.esm.service.api.exception.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.epam.esm.service.api.exception.ErrorCode.ROLE_WITH_SUCH_NAME_NOT_EXIST;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao, ModelMapper modelMapper) {
        this.roleDao = roleDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDto findByName(String name) {
        Optional<Role> foundRole = roleDao.findByName(name);
        if (!foundRole.isPresent()) {
            throw new ServiceException(ROLE_WITH_SUCH_NAME_NOT_EXIST);
        }
        return modelMapper.map(foundRole.get(), RoleDto.class);
    }
}
