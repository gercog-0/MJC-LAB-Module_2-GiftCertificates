package com.epam.esm.dao.api;

import com.epam.esm.dao.api.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    List<User> findAll();
}
