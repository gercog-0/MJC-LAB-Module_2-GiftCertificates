package com.epam.esm.dao.api;

import java.util.List;
import java.util.Optional;

/**
 * The interface Base dao.
 *
 * @param <T> the type parameter
 */
public interface BaseDao<T> {

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<T> findById(Long id);

    /**
     * Add t.
     *
     * @param entity the entity
     * @return the t
     */
    T add(T entity);

    /**
     * Update boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    boolean update(T entity);

    /**
     * Remove boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean remove(Long id);
}
