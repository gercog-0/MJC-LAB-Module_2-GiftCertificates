package com.epam.esm.dao.api.entity;

/**
 * The type Pagination.
 */
public class Pagination {

    private int pageNumber;
    private int size;

    /**
     * Instantiates a new Pagination.
     */
    public Pagination() {
    }

    /**
     * Instantiates a new Pagination.
     *
     * @param pageNumber the page number
     * @param size       the size
     */
    public Pagination(Integer pageNumber, Integer size) {
        this.pageNumber = pageNumber;
        this.size = size;
    }

    /**
     * Gets page number.
     *
     * @return the page number
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets page number.
     *
     * @param pageNumber the page number
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(Integer size) {
        this.size = size;
    }
}
