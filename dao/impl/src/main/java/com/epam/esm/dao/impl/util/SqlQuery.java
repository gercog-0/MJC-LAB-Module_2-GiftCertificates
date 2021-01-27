package com.epam.esm.dao.impl.util;

public final class SqlQuery {

    /*
    queries for gift-certificates table
    */
    public static final String GIFT_CERTIFICATE_FIND_ALL_BY_PARAMETERS = "SELECT gift_certificate.id, " +
            "gift_certificate.name, gift_certificate.description, gift_certificate.price, gift_certificate.duration, " +
            "gift_certificate.create_date, gift_certificate.last_update_date FROM gift_certificate LEFT JOIN " +
            "tag_has_gift_certificate ON gift_certificate.id = tag_has_gift_certificate.gift_certificate_id LEFT JOIN " +
            "tag ON tag_has_gift_certificate.tag_id = tag.id";

    /*
    queries for tag table
    */
    public static final String TAG_FIND_ALL = "SELECT t FROM Tag t";
    public static final String TAG_FIND_BY_NAME = "SELECT t FROM Tag t WHERE t.name = :name";
    public static final String TAG_FIND_MOST_POPULAR_BY_USER = "SELECT t FROM GiftCertificate g JOIN g.tags t " +
            "WHERE g.id IN (SELECT o.giftCertificateId FROM Order o WHERE o.userId = :userId) GROUP BY t.id " +
            "ORDER BY COUNT(t.id) DESC";

    /*
    queries for user table
     */
    public static final String FIND_ALL_USERS = "SELECT u FROM User u";
    public static final String FIND_USER_BY_HIGHEST_AMOUNT_ORDERS = "SELECT u FROM User u WHERE u.id IN " +
            "(SELECT o.userId FROM Order o GROUP BY o.userId ORDER BY SUM(o.cost) DESC)";

    /*
   queries for orders table
    */
    public static final String FIND_ALL_ORDERS_BY_USER_ID = "SELECT o FROM Order o WHERE o.userId =:userId";
    public static final String FIND_ORDER_BY_USER_ID = "SELECT o FROM Order o WHERE o.userId =:userId AND o.id =:id";
}
