package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.RoleDao;
import com.epam.esm.dao.api.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static com.epam.esm.dao.impl.util.SqlQuery.FIND_ROLE_BY_NAME;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Role> findByName(String roleName) {
        return entityManager.createQuery(FIND_ROLE_BY_NAME, Role.class)
                .setParameter("name", roleName)
                .getResultList().stream()
                .findFirst();
    }

    @Override
    public Role add(Role role) {
        entityManager.persist(role);
        return role;
    }
}
