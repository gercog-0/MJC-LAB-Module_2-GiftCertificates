package com.epam.esm.daoimpl;

import com.epam.esm.daoapi.GiftCertificateDao;
import com.epam.esm.daoapi.entity.GiftCertificate;

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

import static com.epam.esm.daoimpl.SqlQuery.GIFT_CERTIFICATE_ADD;
import static com.epam.esm.daoimpl.SqlQuery.GIFT_CERTIFICATE_FIND_ALL;
import static com.epam.esm.daoimpl.SqlQuery.GIFT_CERTIFICATE_FIND_BY_ID;
import static com.epam.esm.daoimpl.SqlQuery.GIFT_CERTIFICATE_REMOVE;
import static com.epam.esm.daoimpl.SqlQuery.GIFT_CERTIFICATE_UPDATE;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificateMapper giftCertificateMapper = new GiftCertificateMapper();

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GiftCertificate> findAll() {
        return jdbcTemplate.query(GIFT_CERTIFICATE_FIND_ALL, giftCertificateMapper);
    }

    @Override
    public Optional<GiftCertificate> findById(long id) {
        return jdbcTemplate.query(GIFT_CERTIFICATE_FIND_BY_ID, new Object[]{id}, giftCertificateMapper)
                .stream().findAny();
    }

    @Override
    public GiftCertificate add(GiftCertificate giftCertificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(GIFT_CERTIFICATE_ADD,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, giftCertificate.getName());
            preparedStatement.setString(2, giftCertificate.getDescription());
            preparedStatement.setBigDecimal(3, giftCertificate.getPrice());
            preparedStatement.setInt(4, giftCertificate.getDuration());
            preparedStatement.setObject(5, giftCertificate.getCreateDate());
            preparedStatement.setObject(6, giftCertificate.getLastUpdateDate());
            return preparedStatement;
        }, keyHolder);
        Number key = keyHolder.getKey();
        if (key != null) {
            giftCertificate.setId(key.longValue());
        }
        return giftCertificate;
    }

    @Override
    public boolean update(GiftCertificate giftCertificate) {
        return jdbcTemplate.update(GIFT_CERTIFICATE_UPDATE,
                giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getDuration(),
                giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate()) > 0;
    }

    @Override
    public boolean remove(long id) {
        return jdbcTemplate.update(GIFT_CERTIFICATE_REMOVE, id) > 0;
    }
}
