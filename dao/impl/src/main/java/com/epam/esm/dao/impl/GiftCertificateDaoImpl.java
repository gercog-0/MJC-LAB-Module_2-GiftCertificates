package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.GiftCertificateDao;
import com.epam.esm.dao.api.entity.GiftCertificate;
import com.epam.esm.dao.api.entity.GiftCertificateQueryParameters;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.dao.impl.util.GiftCertificateSqlQueryCreator;
import com.epam.esm.dao.impl.util.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;


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
    public List<GiftCertificate> findAll(GiftCertificateQueryParameters giftCertificateQueryParameters,
                                         Pagination pagination) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GiftCertificate> criteriaQuery = giftCertificateSqlQueryCreator
                .createQueryByParameters(giftCertificateQueryParameters, criteriaBuilder);
        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(PaginationUtil.defineFirstResultToEntityManager(pagination))
                .setMaxResults(pagination.getSize())
                .getResultList();
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
