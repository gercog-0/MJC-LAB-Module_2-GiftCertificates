package com.epam.esm.dao.impl;


import com.epam.esm.dao.api.TagDao;
import com.epam.esm.dao.api.entity.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.dao.impl.util.SqlQuery.TAG_FIND_ALL;
import static com.epam.esm.dao.impl.util.SqlQuery.TAG_FIND_BY_NAME;
import static com.epam.esm.dao.impl.util.SqlQuery.TAG_FIND_MOST_POPULAR_BY_USER;

@Repository
public class TagDaoImpl implements TagDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tag> findAll() {
        return entityManager.createQuery(TAG_FIND_ALL, Tag.class).getResultList();
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Tag.class, id));
    }

    @Override
    public Tag add(Tag tag) {
        entityManager.persist(tag);
        return tag;
    }

    @Override
    public Tag update(Tag tag) {
        throw new UnsupportedOperationException("Method update for Tag is unsupported.");
    }

    @Override
    public void remove(Long id) {
        Tag foundTag = entityManager.find(Tag.class, id);
        entityManager.remove(foundTag);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return entityManager.createQuery(TAG_FIND_BY_NAME, Tag.class)
                .setParameter("name", name)
                .getResultList().stream()
                .findAny();
    }

    @Override
    public Optional<Tag> findMostPopular(Long userId) {
        return entityManager.createQuery(TAG_FIND_MOST_POPULAR_BY_USER, Tag.class)
                .setParameter("userId", userId)
                .setMaxResults(1)
                .getResultList().stream()
                .findFirst();
    }
}
