package com.epam.esm.dao.api;

import com.epam.esm.dao.api.entity.Tag;
import com.epam.esm.dao.api.entity.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {

    List<User> findAll();
}
