package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.GiftCertificateDao;
import com.epam.esm.dao.api.entity.GiftCertificate;

import com.epam.esm.dao.api.entity.GiftCertificateQueryParameters;
import com.epam.esm.dao.impl.mapper.GiftCertificateMapper;
import com.epam.esm.dao.impl.util.GiftCertificateSqlQueryCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.dao.impl.util.SqlQuery.*;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private static final int LOWER_BOUND_ZERO = 0;

    private final JdbcTemplate jdbcTemplate;
    private final GiftCertificateMapper giftCertificateMapper;
    private final GiftCertificateSqlQueryCreator giftCertificateSqlQueryCreator;

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate, GiftCertificateMapper giftCertificateMapper,
                                  GiftCertificateSqlQueryCreator giftCertificateSqlQueryCreator) {
        this.jdbcTemplate = jdbcTemplate;
        this.giftCertificateMapper = giftCertificateMapper;
        this.giftCertificateSqlQueryCreator = giftCertificateSqlQueryCreator;
    }

    @Override
    public List<GiftCertificate> findAll(GiftCertificateQueryParameters giftCertificateQueryParameters) {
        String partOfQueryByParameters = giftCertificateSqlQueryCreator
                .createByParameters(giftCertificateQueryParameters);
        return jdbcTemplate.query(GIFT_CERTIFICATE_FIND_ALL_BY_PARAMETERS
                + partOfQueryByParameters, giftCertificateMapper);
    }

    @Override
    public Optional<GiftCertificate> findById(Long id) {
        return jdbcTemplate.query(GIFT_CERTIFICATE_FIND_BY_ID, new Object[]{id}, giftCertificateMapper)
                .stream().findAny();
    }

    @Transactional
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
        addTagHasGiftCertificate(giftCertificate);
        return giftCertificate;
    }

    @Transactional
    @Override
    public boolean update(GiftCertificate giftCertificate) {
        removeTagHasGiftCertificate(giftCertificate.getId());
        addTagHasGiftCertificate(giftCertificate);
        return jdbcTemplate.update(GIFT_CERTIFICATE_UPDATE,
                giftCertificate.getName(), giftCertificate.getDescription(),
                giftCertificate.getPrice(), giftCertificate.getDuration(),
                giftCertificate.getCreateDate(), giftCertificate.getLastUpdateDate(),
                giftCertificate.getId()) > LOWER_BOUND_ZERO;
    }

    @Transactional
    @Override
    public boolean remove(Long id) {
        removeTagHasGiftCertificate(id);
        return jdbcTemplate.update(GIFT_CERTIFICATE_REMOVE, id) > LOWER_BOUND_ZERO;
    }

    @Override
    public void removeTagHasGiftCertificate(Long id) {
        jdbcTemplate.update(REMOVE_TAG_HAS_GIFT_CERTIFICATE, id);
    }


    private void addTagHasGiftCertificate(GiftCertificate giftCertificate) {
        giftCertificate.getTags()
                .forEach(tag -> jdbcTemplate.update(ADD_TAG_HAS_GIFT_CERTIFICATE, tag.getId(), giftCertificate.getId()));
    }
}
