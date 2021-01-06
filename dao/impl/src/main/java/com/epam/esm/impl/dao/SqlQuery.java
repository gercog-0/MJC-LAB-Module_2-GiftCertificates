package com.epam.esm.impl.dao;

public final class SqlQuery {
    /*
    queries for gift-certificates table
    */
    public static final String GIFT_CERTIFICATE_FIND_ALL = "SELECT id, name, description, price, duration, " +
            "create_date, last_update_date FROM gift-certificate";
    public static final String GIFT_CERTIFICATE_FIND_BY_ID = "SELECT id, name, description, price, duration, " +
            "create_date, last_update_date FROM gift-certificate WHERE id = ?";
    public static final String GIFT_CERTIFICATE_ADD = "INSERT INTO gift-certificate (name, description, price, " +
            "duration, create_date, last_update_date) VALUES(?,?,?,?,?,?)";
    public static final String GIFT_CERTIFICATE_UPDATE = "UPDATE gift-certificate SET name = ?, description = ?, " +
            "price = ?, duration = ?, create_date = ?, last_update_date = ? WHERE id = ?";
    public static final String GIFT_CERTIFICATE_REMOVE = "DELETE FROM gift-certificate WHERE id = ?";

    /*
    queries for tag table
    */
    public static final String TAG_FIND_ALL = "SELECT id, name FROM tag";
    public static final String TAG_FIND_BY_ID = "SELECT id, name FROM tag WHERE id = ?";
    public static final String TAG_ADD = "INSERT INTO tag (name) VALUES (?)";
    public static final String TAG_REMOVE = "DELETE FROM tag WHERE id = ?";

}
