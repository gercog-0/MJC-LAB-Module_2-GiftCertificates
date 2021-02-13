package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.RoleDto;

public interface RoleService {

    RoleDto findByName(String name);
}
