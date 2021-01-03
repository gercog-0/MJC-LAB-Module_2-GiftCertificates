package com.epam.esm.dao;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SqlColumnName {
    /*
    gift-certificates table.
    */
    public final String GIFT_CERTIFICATE_ID = "id";
    public final String GIFT_CERTIFICATE_NAME = "name";
    public final String GIFT_CERTIFICATE_DESCRIPTION = "description";
    public final String GIFT_CERTIFICATE_PRICE = "price";
    public final String GIFT_CERTIFICATE_DURATION = "duration";
    public final String GIFT_CERTIFICATE_CREATE_DATE = "create_date";
    public final String GIFT_CERTIFICATE_LAST_UPDATE_DATE = "last_update_date";

    /*
    tag table.
     */
    public final String TAG_ID = "id";
    public final String TAG_NAME = "name";

}
