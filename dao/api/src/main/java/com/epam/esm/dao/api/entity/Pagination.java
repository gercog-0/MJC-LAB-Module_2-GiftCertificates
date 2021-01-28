package com.epam.esm.dao.api.entity;

public class Pagination {

    private int pageNumber;
    private int size;

    public Pagination() {
    }

    public Pagination(Integer pageNumber, Integer size) {
        this.pageNumber = pageNumber;
        this.size = size;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
