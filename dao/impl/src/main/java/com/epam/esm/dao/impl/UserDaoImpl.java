package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.UserDao;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.dao.api.entity.User;
import com.epam.esm.dao.impl.util.PaginationUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.dao.impl.util.SqlQuery.FIND_ALL_USERS;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll(Pagination pagination) {
        return entityManager.createQuery(FIND_ALL_USERS, User.class)
                .setFirstResult(PaginationUtil.defineFirstResultToEntityManager(pagination))
                .setMaxResults(pagination.getSize())
                .getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public User add(User user) {
        throw new UnsupportedOperationException("Method add for User is unsupported.");
    }

    @Override
    public User update(User user) {
        throw new UnsupportedOperationException("Method update for User is unsupported.");

    }

    @Override
    public void remove(Long id) {
        throw new UnsupportedOperationException("Method remove for User is unsupported.");
    }
}
