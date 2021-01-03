package com.epam.esm.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
    List<T> findAll();

    Optional<T> findById(int id);

    T add(T t);

    T update(T t);

    void remove(long id);
}
