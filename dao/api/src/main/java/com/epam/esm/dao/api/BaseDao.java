package com.epam.esm.dao.api;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    T add(T t);

    boolean update(T t);

    boolean remove(Long id);
}
