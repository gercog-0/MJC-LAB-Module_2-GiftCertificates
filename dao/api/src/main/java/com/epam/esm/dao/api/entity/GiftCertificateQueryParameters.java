package com.epam.esm.dao.api.entity;

/**
 * Class {@code GiftCertificateQueryParameters} represents a class
 * that contains parameters for generating a dynamic query .
 *
 * @author Ivan Yanushkevich
 * @version 1.0
 */
public class GiftCertificateQueryParameters {

    /**
     * The enum Order sort.
     */
    public enum OrderSort {
        /**
         * Asc order sort.
         */
        ASC("ASC"),
        /**
         * Desc order sort.
         */
        DESC("DESC");

        private final String expression;

        OrderSort(String expression){
            this.expression = expression;
        }

        /**
         * Gets expression.
         *
         * @return the expression
         */
        public String getExpression() {
            return expression;
        }
    }

    /**
     * The enum Type sort.
     */
    public enum TypeSort {
        /**
         * Name type sort.
         */
        NAME("gift_certificate.name"),
        /**
         * Create date type sort.
         */
        CREATE_DATE("gift_certificate.create_date");

        private final String expression;

        TypeSort(String expression) {
            this.expression = expression;
        }

        /**
         * Gets expression.
         *
         * @return the expression
         */
        public String getExpression() {
            return expression;
        }
    }

    private String tagName;
    private String name;
    private String description;
    private TypeSort typeSort;
    private OrderSort orderSort;

    /**
     * Instantiates a new Gift certificate query parameters.
     */
    public GiftCertificateQueryParameters() {
    }

    /**
     * Instantiates a new Gift certificate query parameters.
     *
     * @param tagName     the tag name
     * @param name        the name
     * @param description the description
     * @param typeSort    the type sort
     * @param orderSort   the order sort
     */
    public GiftCertificateQueryParameters(String tagName, String name, String description, TypeSort typeSort, OrderSort orderSort) {
        this.tagName = tagName;
        this.name = name;
        this.description = description;
        this.typeSort = typeSort;
        this.orderSort = orderSort;
    }

    /**
     * Gets tag name.
     *
     * @return the tag name
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Sets tag name.
     *
     * @param tagName the tag name
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
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
