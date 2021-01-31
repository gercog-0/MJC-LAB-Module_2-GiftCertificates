package com.epam.esm.dao.api;

import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.dao.api.entity.User;

import java.util.List;

/**
 * The interface User dao.
 */
public interface UserDao extends BaseDao<User> {

    /**
     * Find all list.
     *
     * @param pagination the pagination
     * @return the list
     */
    List<User> findAll(Pagination pagination);
}
