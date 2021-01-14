package com.epam.esm.dao.api;

import java.util.Optional;

/**
 * Interface {@code BaseDao} describes CRUD operations for working with database tables.
 *
 * @param <T> the type parameter (entity)
 * @author Ivan Yanushkevich
 * @version 1.0
 */
public interface BaseDao<T> {

    /**
     * Find by id optional.
     *
     * @param id of entity to find
     * @return the optional of entity
     */
    Optional<T> findById(Long id);

    /**
     * Add entity.
     *
     * @param entity to add
     * @return the entity
     */
    T add(T entity);

    /**
     * Update boolean.
     *
     * @param entity to update
     * @return the boolean
     */
    boolean update(T entity);

    /**
     * Remove boolean.
     *
     * @param id of entity to delete
     * @return the boolean
     */
    boolean remove(Long id);
}
