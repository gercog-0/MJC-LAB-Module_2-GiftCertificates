package com.epam.esm.dao.api;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {

    Optional<T> findById(Long id);

    T add(T type);

    boolean update(T type);

    boolean remove(Long id);

    void removeTagHasGiftCertificate(Long id);
}
