package com.epam.esm.dao.impl.util;

import com.epam.esm.dao.api.entity.GiftCertificateQueryParameters;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class GiftCertificateSqlQueryCreator {

    private final String TAG_NAME = " tag.name LIKE '%s'";
    private static final String GIFT_CERTIFICATE_NAME = " gift_certificate.name LIKE ''%{0}%''";
    private static final String GIFT_CERTIFICATE_DESCRIPTION = " gift_certificate.description LIKE ''%{0}%''";
    private static final String SQL_WHERE = " WHERE ";
    private static final String SQL_AND = " AND ";
    private static final String SQL_GROUP_BY = " GROUP BY gift_certificate.id";
    private static final String SQL_ORDER_BY = " ORDER BY ";
    private static final String SPACE = " ";

    public String createByParameters(GiftCertificateQueryParameters giftCertificateQueryParameters) {
        StringBuilder stringBuilder = new StringBuilder();
        String tagName = giftCertificateQueryParameters.getTagName();
        if (tagName != null) {
            addOperator(stringBuilder);
            stringBuilder.append(String.format(TAG_NAME, tagName));
        }
        String giftCertificateName = giftCertificateQueryParameters.getName();
        if (giftCertificateName != null) {
            addOperator(stringBuilder);
            stringBuilder.append(MessageFormat.format(GIFT_CERTIFICATE_NAME, giftCertificateName));
        }
        String giftCertificateDescription = giftCertificateQueryParameters.getDescription();
        if (giftCertificateDescription != null) {
            addOperator(stringBuilder);
            stringBuilder.append(MessageFormat.format(GIFT_CERTIFICATE_DESCRIPTION, giftCertificateDescription));
        }
        stringBuilder.append(SQL_GROUP_BY);
        GiftCertificateQueryParameters.TypeSort typeSort = giftCertificateQueryParameters.getTypeSort();
        GiftCertificateQueryParameters.OrderSort orderSort = giftCertificateQueryParameters.getOrderSort();
        if (typeSort != null) {
            stringBuilder.append(SQL_ORDER_BY + typeSort.getExpression());
            if (orderSort != null) {
                stringBuilder.append(SPACE + orderSort.getExpression());
            }
        }
        return stringBuilder.toString();
    }

    private void addOperator(StringBuilder stringBuilder) {
        if (stringBuilder.toString().isEmpty()) {
            stringBuilder.append(SQL_WHERE);
        } else {
            stringBuilder.append(SQL_AND);
        }
    }
}
