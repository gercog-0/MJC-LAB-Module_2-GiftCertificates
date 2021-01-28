package com.epam.esm.service.api.dto;

public class PaginationDto {

    private Integer pageNumber;
    private Integer size;

    public PaginationDto() {
    }

    public PaginationDto(Integer pageNumber, Integer size) {
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
