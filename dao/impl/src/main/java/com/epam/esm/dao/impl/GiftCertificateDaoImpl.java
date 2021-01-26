package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.GiftCertificateDao;
import com.epam.esm.dao.api.entity.GiftCertificate;
import com.epam.esm.dao.api.entity.GiftCertificateQueryParameters;
import com.epam.esm.dao.impl.util.GiftCertificateSqlQueryCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.dao.impl.util.SqlQuery.GIFT_CERTIFICATE_FIND_ALL_BY_PARAMETERS;

@Repository
public class GiftCertificateDaoImpl implements GiftCertificateDao {

    private final GiftCertificateSqlQueryCreator giftCertificateSqlQueryCreator;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public GiftCertificateDaoImpl(GiftCertificateSqlQueryCreator giftCertificateSqlQueryCreator) {
        this.giftCertificateSqlQueryCreator = giftCertificateSqlQueryCreator;
    }

    @Override
    public List<GiftCertificate> findAll(GiftCertificateQueryParameters giftCertificateQueryParameters) {
        String partOfQueryByParameters = giftCertificateSqlQueryCreator
                .createByParameters(giftCertificateQueryParameters);
        return entityManager.createNativeQuery(GIFT_CERTIFICATE_FIND_ALL_BY_PARAMETERS +
                partOfQueryByParameters, GiftCertificate.class).getResultList();
    }

    @Override
    public Optional<GiftCertificate> findById(Long id) {
        return Optional.ofNullable(entityManager.find(GiftCertificate.class, id));
    }

    @Override
    public GiftCertificate add(GiftCertificate giftCertificate) {
        entityManager.persist(giftCertificate);
        return giftCertificate;
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        entityManager.merge(giftCertificate);
        return giftCertificate;
    }

    @Override
    public void remove(Long id) {
        GiftCertificate foundGiftCertificate = entityManager.find(GiftCertificate.class, id);
        entityManager.remove(foundGiftCertificate);
    }
}
