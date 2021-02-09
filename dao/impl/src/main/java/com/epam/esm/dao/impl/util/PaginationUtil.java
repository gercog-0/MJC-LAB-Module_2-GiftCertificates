package com.epam.esm.dao.impl.util;

import com.epam.esm.dao.api.entity.Pagination;

public final class PaginationUtil {

    private PaginationUtil() {
    }

    public static int defineFirstResultToEntityManager(Pagination pagination) {
        return (pagination.getPageNumber() - 1) * pagination.getSize();
    }
}
