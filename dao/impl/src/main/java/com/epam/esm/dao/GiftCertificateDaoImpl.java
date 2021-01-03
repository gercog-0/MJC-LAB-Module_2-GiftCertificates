package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;

import com.epam.esm.mapper.GiftCertificateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.dao.SqlQuery.*;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query(GIFT_CERTIFICATE_FIND_ALL, new GiftCertificateMapper());
    }

    @Override
    public Optional<GiftCertificate> findById(int id) {
        return jdbcTemplate.query(GIFT_CERTIFICATE_FIND_BY_ID, new Object[]{id}, new GiftCertificateMapper())
                .stream().findAny();
    }

    @Override
    public GiftCertificate add(GiftCertificate giftCertificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean isAdded = jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(GIFT_CERTIFICATE_ADD,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, giftCertificate.getName());
            preparedStatement.setString(2, giftCertificate.getDescription());
            preparedStatement.setBigDecimal(3, giftCertificate.getPrice());
            preparedStatement.setInt(4, giftCertificate.getDuration());
            preparedStatement.setObject(5, giftCertificate.getCreateDate());
            preparedStatement.setObject(6, giftCertificate.getLastUpdateDate());
            return preparedStatement;
        }, keyHolder) > 0;
        Number key = keyHolder.getKey();
        if (key != null) {
            giftCertificate.setId(key.longValue());
        }
        return giftCertificate;
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        jdbcTemplate.update(GIFT_CERTIFICATE_UPDATE,
                giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getDuration(),
                giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate());
        return giftCertificate;
    }

    @Override
    public void remove(long id) {
        jdbcTemplate.update(GIFT_CERTIFICATE_REMOVE, id);
    }
}
