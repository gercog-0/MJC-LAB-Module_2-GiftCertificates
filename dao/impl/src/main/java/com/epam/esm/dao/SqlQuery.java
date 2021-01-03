package com.epam.esm.dao;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SqlQuery {
    /*
    queries for gift-certificates table
    */
    public final String GIFT_CERTIFICATE_FIND_ALL = "SELECT id, name, description, price, duration, " +
            "create_date, last_update_date FROM gift-certificate";
    public final String GIFT_CERTIFICATE_FIND_BY_ID = "SELECT id, name, description, price, duration, " +
            "create_date, last_update_date FROM gift-certificate WHERE id = ?";
    public final String GIFT_CERTIFICATE_ADD = "INSERT INTO gift-certificate (name, description, price, " +
            "duration, create_date, last_update_date) VALUES(?,?,?,?,?,?)";
    public final String GIFT_CERTIFICATE_UPDATE = "UPDATE gift-certificate SET name = ?, description = ?, " +
            "price = ?, duration = ?, create_date = ?, last_update_date = ? WHERE id = ?";
    public final String GIFT_CERTIFICATE_REMOVE = "DELETE FROM gift-certificate WHERE id = ?";

    /*
    queries for tag table
    */
    public final String TAG_FIND_ALL = "SELECT id, name FROM tag";
    public final String TAG_FIND_BY_ID = "SELECT id, name FROM tag WHERE id = ?";
    public final String TAG_ADD = "INSERT INTO tag (name) VALUES (?)";
    public final String TAG_REMOVE = "DELETE FROM tag WHERE id = ?";

}
