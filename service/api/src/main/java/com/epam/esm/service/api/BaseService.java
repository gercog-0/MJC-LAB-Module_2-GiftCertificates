package com.epam.esm.service.api;

import java.util.List;

public interface BaseService<T> {

    List<T> findAll();

    T findById(long id);

    T add(T type);

    void remove(long id);
}
