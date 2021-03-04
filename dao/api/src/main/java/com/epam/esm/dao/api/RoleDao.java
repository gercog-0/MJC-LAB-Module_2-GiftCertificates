package com.epam.esm.dao.api;

import com.epam.esm.dao.api.entity.Role;

import java.util.Optional;

public interface RoleDao {

    Optional<Role> findByName(String roleName);

    Role add(Role role);
}
