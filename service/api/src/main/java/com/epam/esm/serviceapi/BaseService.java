package com.epam.esm.serviceapi;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> findAll();

    T findById(String id);

    T add(T t);

    void remove(String id);
}
