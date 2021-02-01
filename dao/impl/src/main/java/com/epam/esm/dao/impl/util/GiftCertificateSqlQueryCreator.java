package com.epam.esm.dao.impl.util;

import com.epam.esm.dao.api.entity.GiftCertificate;
import com.epam.esm.dao.api.entity.GiftCertificateQueryParameters;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GiftCertificateSqlQueryCreator {

    private static final String GIFT_CERTIFICATE_DESCRIPTION = "description";
    private static final String NAME = "name";
    private static final String GIFT_CERTIFICATE_TAGS = "tags";
    private static final String PERCENT = "%";


    public CriteriaQuery<GiftCertificate> createQueryByParameters(GiftCertificateQueryParameters
                                                                          giftCertificateQueryParameters,
                                                                  CriteriaBuilder criteriaBuilder) {
        CriteriaQuery<GiftCertificate> criteriaQuery = criteriaBuilder.createQuery(GiftCertificate.class);
        Root<GiftCertificate> giftCertificateRoot = criteriaQuery.from(GiftCertificate.class);
        List<Predicate> conditions = new ArrayList<>();
        addSearchName(giftCertificateQueryParameters.getName(), criteriaBuilder, giftCertificateRoot, conditions);
        addSearchDescription(giftCertificateQueryParameters.getDescription(), criteriaBuilder, giftCertificateRoot, conditions);
        addSearchTagNames(giftCertificateQueryParameters.getTags(), criteriaBuilder, giftCertificateRoot, conditions);
        criteriaQuery.select(giftCertificateRoot).where(conditions.toArray(new Predicate[]{}));
        addSearchSortType(giftCertificateQueryParameters, criteriaBuilder, criteriaQuery, giftCertificateRoot);
        return criteriaQuery;
    }

    private void addSearchTagNames(List<String> listTags, CriteriaBuilder criteriaBuilder,
                                   Root<GiftCertificate> giftCertificateRoot, List<Predicate> conditions) {
        if (listTags != null) {
            conditions.addAll(listTags.stream()
                    .map(tag -> criteriaBuilder.equal(giftCertificateRoot.join(GIFT_CERTIFICATE_TAGS).get(NAME), tag))
                    .collect(Collectors.toList()));
        }
    }

    private void addSearchSortType(GiftCertificateQueryParameters giftCertificateQueryParameters,
                                   CriteriaBuilder criteriaBuilder, CriteriaQuery<GiftCertificate> criteriaQuery,
                                   Root<GiftCertificate> giftCertificateRoot) {
        if (giftCertificateQueryParameters.getTypeSort() != null) {
            String sortTypeString = giftCertificateQueryParameters.getTypeSort().getExpression();
            if (giftCertificateQueryParameters.getOrderSort() != null
                    && giftCertificateQueryParameters.getOrderSort().equals(GiftCertificateQueryParameters.OrderSort.DESC)) {
                criteriaQuery.orderBy(criteriaBuilder.desc(giftCertificateRoot.get(sortTypeString)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.asc(giftCertificateRoot.get(sortTypeString)));
            }
        }
    }

    private void addSearchDescription(String description, CriteriaBuilder criteriaBuilder,
                                      Root<GiftCertificate> giftCertificateRoot, List<Predicate> conditions) {
        if (description != null) {
            conditions.add(criteriaBuilder.like(giftCertificateRoot.get(GIFT_CERTIFICATE_DESCRIPTION), PERCENT + description + PERCENT));
        }
    }

    private void addSearchName(String name, CriteriaBuilder criteriaBuilder, Root<GiftCertificate> giftCertificateRoot,
                               List<Predicate> conditions) {
        if (name != null) {
            conditions.add(criteriaBuilder.like(giftCertificateRoot.get(NAME), PERCENT + name + PERCENT));
        }
    }
}
