package com.epam.esm.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
    List<T> findAll();

    Optional<T> findById(int id);

    T add(T t);

    boolean update(T t);

    boolean remove(long id);
}
