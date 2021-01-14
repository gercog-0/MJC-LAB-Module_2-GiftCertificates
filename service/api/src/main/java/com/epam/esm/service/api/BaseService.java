package com.epam.esm.service.api;


/**
 * The interface Base service.
 *
 * @param <T> the type parameter
 */
public interface BaseService<T> {

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    T findById(long id);

    /**
     * Add t.
     *
     * @param entity the entity
     * @return the t
     */
    T add(T entity);

    /**
     * Remove.
     *
     * @param id the id
     */
    void remove(long id);
}
