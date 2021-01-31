package com.epam.esm.service.api.dto;

/**
 * The type Pagination dto.
 */
public class PaginationDto {

    private Integer pageNumber;
    private Integer size;

    /**
     * Instantiates a new Pagination dto.
     */
    public PaginationDto() {
    }

    /**
     * Instantiates a new Pagination dto.
     *
     * @param pageNumber the page number
     * @param size       the size
     */
    public PaginationDto(Integer pageNumber, Integer size) {
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
