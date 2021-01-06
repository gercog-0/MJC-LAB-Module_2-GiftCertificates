package com.epam.esm.service.api;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    List<T> findAll();

    T findById(Long id);

    T add(T t);

    void remove(Long id);
}
