package com.epam.esm.service.api;


public interface BaseService<T> {

    T findById(long id);

    T add(T entity);

    void remove(long id);
}
