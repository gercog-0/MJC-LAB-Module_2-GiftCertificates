package com.epam.esm.service.api.dto;

public class GiftCertificateQueryParametersDto {

    public enum OrderSort {
        ASC,
        DESC
    }

    public enum TypeSort {
        NAME,
        CREATE_DATE
    }

    private String tagName;
    private String name;
    private String description;
    private TypeSort typeSort;
    private OrderSort orderSort;

    public GiftCertificateQueryParametersDto() {
    }

    public GiftCertificateQueryParametersDto(String tagName, String name, String description, TypeSort typeSort, OrderSort orderSort) {
        this.tagName = tagName;
        this.name = name;
        this.description = description;
        this.typeSort = typeSort;
        this.orderSort = orderSort;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeSort getTypeSort() {
        return typeSort;
    }

    public void setTypeSort(TypeSort typeSort) {
        this.typeSort = typeSort;
    }

    public OrderSort getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(OrderSort orderSort) {
        this.orderSort = orderSort;
    }
}
