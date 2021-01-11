package com.epam.esm.service.api;


public interface BaseService<T> {

    T findById(long id);

    T add(T type);

    void remove(long id);
}
