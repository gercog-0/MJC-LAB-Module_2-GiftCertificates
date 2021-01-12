package com.epam.esm.dao.impl.util;

public final class SqlQuery {

    /*
    queries for gift-certificates table
    */
    public static final String GIFT_CERTIFICATE_FIND_ALL = "SELECT id, name, description, price, duration, " +
            "create_date, last_update_date FROM gift_certificate";
    public static final String GIFT_CERTIFICATE_FIND_ALL_BY_PARAMETERS = "SELECT gift_certificate.id, " +
            "gift_certificate.name, gift_certificate.description, gift_certificate.price, gift_certificate.duration, " +
            "gift_certificate.create_date, gift_certificate.last_update_date FROM gift_certificate LEFT JOIN " +
            "tag_has_gift_certificate ON gift_certificate.id = tag_has_gift_certificate.gift_certificate_id LEFT JOIN " +
            "tag ON tag_has_gift_certificate.tag_id = tag.id";
    public static final String GIFT_CERTIFICATE_FIND_BY_ID = "SELECT id, name, description, price, duration, " +
            "create_date, last_update_date FROM gift_certificate WHERE id = ?";
    public static final String GIFT_CERTIFICATE_ADD = "INSERT INTO gift_certificate (name, description, price, " +
            "duration, create_date, last_update_date) VALUES(?,?,?,?,?,?)";
    public static final String GIFT_CERTIFICATE_UPDATE = "UPDATE gift_certificate SET name = ?, description = ?, " +
            "price = ?, duration = ?, create_date = ?, last_update_date = ? WHERE id = ?";
    public static final String GIFT_CERTIFICATE_REMOVE = "DELETE FROM gift_certificate WHERE id = ?";

    /*
    queries for tag table
    */
    public static final String TAG_FIND_ALL = "SELECT id, name FROM tag";
    public static final String TAG_FIND_BY_ID = "SELECT id, name FROM tag WHERE id = ?";
    public static final String TAG_FIND_BY_NAME = "SELECT id, name FROM tag WHERE name = ?";
    public static final String TAG_ADD = "INSERT INTO tag (name) VALUES (?)";
    public static final String TAG_REMOVE = "DELETE FROM tag WHERE id = ?";

    /*
    queries for tag_has_gift-certificate table
     */
    public static final String FIND_TAGS_BY_GIFT_CERTIFICATE_ID = "SELECT tag.id, tag.name FROM tag JOIN " +
            "tag_has_gift_certificate ON tag.id = tag_has_gift_certificate.tag_id WHERE " +
            "tag_has_gift_certificate.gift_certificate_id = ?";
    public static final String ADD_TAG_HAS_GIFT_CERTIFICATE = "INSERT INTO tag_has_gift_certificate " +
            "(tag_id, gift_certificate_id) VALUES (?,?)";
    public static final String REMOVE_TAG_HAS_GIFT_CERTIFICATE = "DELETE FROM tag_has_gift_certificate " +
            "WHERE gift_certificate_id = ?";
    public static final String REMOVE_TAG_HAS_GIFT_CERTIFICATE_BY_TAG = "DELETE FROM tag_has_gift_certificate " +
            "WHERE tag_id = ?";
}
