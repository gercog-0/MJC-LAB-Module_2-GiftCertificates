package com.epam.esm.mapper;

import com.epam.esm.daoapi.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static com.epam.esm.daoimpl.SqlColumnName.GIFT_CERTIFICATE_ID;
import static com.epam.esm.daoimpl.SqlColumnName.GIFT_CERTIFICATE_NAME;
import static com.epam.esm.daoimpl.SqlColumnName.GIFT_CERTIFICATE_DESCRIPTION;
import static com.epam.esm.daoimpl.SqlColumnName.GIFT_CERTIFICATE_PRICE;
import static com.epam.esm.daoimpl.SqlColumnName.GIFT_CERTIFICATE_DURATION;
import static com.epam.esm.daoimpl.SqlColumnName.GIFT_CERTIFICATE_CREATE_DATE;
import static com.epam.esm.daoimpl.SqlColumnName.GIFT_CERTIFICATE_LAST_UPDATE_DATE;

public class GiftCertificateMapper implements RowMapper<GiftCertificate> {
    @Override
    public GiftCertificate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new GiftCertificate(
                resultSet.getLong(GIFT_CERTIFICATE_ID),
                resultSet.getString(GIFT_CERTIFICATE_NAME),
                resultSet.getString(GIFT_CERTIFICATE_DESCRIPTION),
                resultSet.getBigDecimal(GIFT_CERTIFICATE_PRICE),
                resultSet.getInt(GIFT_CERTIFICATE_DURATION),
                LocalDateTime.of(resultSet.getDate(GIFT_CERTIFICATE_CREATE_DATE).toLocalDate(),
                        resultSet.getTime(GIFT_CERTIFICATE_CREATE_DATE).toLocalTime()),
                LocalDateTime.of(resultSet.getDate(GIFT_CERTIFICATE_LAST_UPDATE_DATE).toLocalDate(),
                        resultSet.getTime(GIFT_CERTIFICATE_LAST_UPDATE_DATE).toLocalTime())
        );
    }
}
