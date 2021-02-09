package com.epam.esm.service.api.dto;

import java.util.List;

/**
 * The type Gift certificate query parameters dto.
 */
public class GiftCertificateQueryParametersDto {

    /**
     * The enum Order sort.
     */
    public enum OrderSort {
        /**
         * Asc order sort.
         */
        ASC,
        /**
         * Desc order sort.
         */
        DESC
    }

    /**
     * The enum Type sort.
     */
    public enum TypeSort {
        /**
         * Name type sort.
         */
        NAME,
        /**
         * Create date type sort.
         */
        CREATE_DATE
    }

    private List<String> tags;
    private String name;
    private String description;
    private TypeSort typeSort;
    private OrderSort orderSort;

    /**
     * Instantiates a new Gift certificate query parameters dto.
     */
    public GiftCertificateQueryParametersDto() {
    }

    /**
     * Instantiates a new Gift certificate query parameters dto.
     *
     * @param tags        the tags
     * @param name        the name
     * @param description the description
     * @param typeSort    the type sort
     * @param orderSort   the order sort
     */
    public GiftCertificateQueryParametersDto(List<String> tags, String name, String description, TypeSort typeSort, OrderSort orderSort) {
        this.tags = tags;
        this.name = name;
        this.description = description;
        this.typeSort = typeSort;
        this.orderSort = orderSort;
    }

    /**
     * Gets tags.
     *
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Sets tags.
     *
     * @param tags the tags
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets type sort.
     *
     * @return the type sort
     */
    public TypeSort getTypeSort() {
        return typeSort;
    }

    /**
     * Sets type sort.
     *
     * @param typeSort the type sort
     */
    public void setTypeSort(TypeSort typeSort) {
        this.typeSort = typeSort;
    }

    /**
     * Gets order sort.
     *
     * @return the order sort
     */
    public OrderSort getOrderSort() {
        return orderSort;
    }

    /**
     * Sets order sort.
     *
     * @param orderSort the order sort
     */
    public void setOrderSort(OrderSort orderSort) {
        this.orderSort = orderSort;
    }
}
