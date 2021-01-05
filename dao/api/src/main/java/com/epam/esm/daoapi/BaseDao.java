package com.epam.esm.daoapi;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
    List<T> findAll();

    Optional<T> findById(long id);

    T add(T t);

    boolean update(T t);

    boolean remove(long id);
}
