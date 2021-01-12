package com.epam.esm.dao.api;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {

    Optional<T> findById(Long id);

    T add(T entity);

    boolean update(T entity);

    boolean remove(Long id);

    void removeTagHasGiftCertificate(Long id);
}
