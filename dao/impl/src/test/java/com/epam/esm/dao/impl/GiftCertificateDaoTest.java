package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.GiftCertificateDao;
import com.epam.esm.dao.api.entity.GiftCertificate;
import com.epam.esm.dao.api.entity.GiftCertificateQueryParameters;
import com.epam.esm.dao.impl.mapper.GiftCertificateMapper;
import com.epam.esm.dao.impl.util.GiftCertificateSqlQueryCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GiftCertificateDaoTest {

    private GiftCertificateDao giftCertificateDao;

    @BeforeEach
    void setUp() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("classpath:script/create_table_gift_certificate.sql")
                .addScript("classpath:script/fill_table_gift_certificate.sql")
                .addScript("classpath:script/create_table_tag_has_gift_certificate.sql")
                .addScript("classpath:script/fill_table_tag_has_gift_certificate.sql")
                .addScript("classpath:script/create_table_tag.sql")
                .addScript("classpath:script/fill_table_tag.sql")
                .build();
        GiftCertificateMapper giftCertificateMapper = new GiftCertificateMapper();
        GiftCertificateSqlQueryCreator giftCertificateSqlQueryCreator = new GiftCertificateSqlQueryCreator();
        giftCertificateDao = new GiftCertificateDaoImpl(new JdbcTemplate(dataSource), giftCertificateMapper,
                giftCertificateSqlQueryCreator);
    }

    @AfterEach
    void tierDown() {
        new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("classpath:script/delete_table_gift_certificate.sql")
                .addScript("classpath:script/delete_table_tag_has_gift_certificate.sql")
                .addScript("classpath:script/delete_table_tag.sql")
                .build();
        giftCertificateDao = null;
    }

    @Test
    void findAllShouldReturnCorrectListSizeOfGiftCertificates() {
        int expectedSize = 4;

        int actualSize = giftCertificateDao.findAll(new GiftCertificateQueryParameters()).size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void findAllShouldReturnIncorrectListSizeOfGiftCertificates() {
        int expectedSize = 0;

        int actualSize = giftCertificateDao.findAll(new GiftCertificateQueryParameters()).size();

        assertNotEquals(expectedSize, actualSize);
    }

    @Test
    void findByIdCorrectIdShouldReturnNotEmptyOptional() {
        GiftCertificate expectedGiftCertificate = new GiftCertificate();
        expectedGiftCertificate.setId(1L);
        expectedGiftCertificate.setName("Spa");
        expectedGiftCertificate.setDescription("Hot relax to you");
        expectedGiftCertificate.setPrice(new BigDecimal("500.00"));
        expectedGiftCertificate.setDuration(2);
        expectedGiftCertificate.setCreateDate(LocalDateTime
                .of(2020, 9, 12, 15, 0, 0));
        expectedGiftCertificate.setLastUpdateDate(LocalDateTime
                .of(2021, 1, 1, 17, 0, 0));

        Optional<GiftCertificate> actualGiftCertificate = giftCertificateDao.findById(1L);

        assertEquals(Optional.of(expectedGiftCertificate), actualGiftCertificate);
    }

    @Test
    void findByIdIncorrectIdShouldReturnEmptyOptional() {
        Optional<GiftCertificate> expectedOptional = Optional.empty();

        Optional<GiftCertificate> actualOptional = giftCertificateDao.findById(5L);

        assertEquals(expectedOptional, actualOptional);
    }

    @Test
    void addCorrectDataShouldReturnGiftCertificate() {
        GiftCertificate expectedGiftCertificate = new GiftCertificate();
        expectedGiftCertificate.setName("New certificate");
        expectedGiftCertificate.setDescription("Just a free");
        expectedGiftCertificate.setPrice(new BigDecimal("1"));
        expectedGiftCertificate.setDuration(3);
        expectedGiftCertificate.setCreateDate(LocalDateTime
                .of(2020, 9, 12, 15, 0, 0));
        expectedGiftCertificate.setLastUpdateDate(LocalDateTime
                .of(2021, 1, 1, 17, 0, 0));

        expectedGiftCertificate.setTags(new ArrayList<>());

        GiftCertificate actualGiftCertificate = giftCertificateDao.add(expectedGiftCertificate);
        assertNotNull(actualGiftCertificate);
    }

    @Test
    void addIncorrectDataShouldThrowException() {
        GiftCertificate expectedGiftCertificate = new GiftCertificate();
        expectedGiftCertificate.setName("New certificate name incorrect because its can be less than 45 symbols");
        expectedGiftCertificate.setDescription("Just a free");
        expectedGiftCertificate.setPrice(new BigDecimal("100"));
        expectedGiftCertificate.setDuration(3);
        expectedGiftCertificate.setCreateDate(LocalDateTime
                .of(2020, 9, 12, 15, 0, 0));
        expectedGiftCertificate.setLastUpdateDate(LocalDateTime
                .of(2021, 1, 1, 17, 0, 0));
        expectedGiftCertificate.setTags(new ArrayList<>());

        assertThrows(DataIntegrityViolationException.class, () -> giftCertificateDao.add(expectedGiftCertificate));
    }

    @Test
    void updateCorrectDataShouldReturnUpdatedGiftCertificate() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(4L);
        giftCertificate.setName("Marker");
        giftCertificate.setDescription("Spend money");
        giftCertificate.setPrice(new BigDecimal("175"));
        giftCertificate.setDuration(1);
        giftCertificate.setCreateDate(LocalDateTime
                .of(2020, 9, 12, 15, 0, 0));
        giftCertificate.setLastUpdateDate(LocalDateTime
                .of(2021, 1, 1, 17, 0, 0));
        giftCertificate.setTags(new ArrayList<>());

        GiftCertificate expectedGiftCertificate = new GiftCertificate();
        expectedGiftCertificate.setId(4L);
        expectedGiftCertificate.setName("Marker");
        expectedGiftCertificate.setDescription("Spend money");
        expectedGiftCertificate.setPrice(new BigDecimal("175"));
        expectedGiftCertificate.setDuration(1);
        expectedGiftCertificate.setCreateDate(LocalDateTime
                .of(2020, 9, 12, 15, 0, 0));
        expectedGiftCertificate.setLastUpdateDate(LocalDateTime
                .of(2021, 1, 1, 17, 0, 0));
        expectedGiftCertificate.setTags(new ArrayList<>());

        boolean condition = giftCertificateDao.update(giftCertificate);

        assertTrue(condition);
    }

    @Test
    void updateIncorrectDataShouldThrowException() {
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setId(4L);
        giftCertificate.setName("New certificate name incorrect because its can be less than 45 symbols");
        giftCertificate.setDescription("Spend money");
        giftCertificate.setPrice(new BigDecimal("175"));
        giftCertificate.setDuration(1);
        giftCertificate.setCreateDate(LocalDateTime
                .of(2020, 9, 12, 15, 0, 0));
        giftCertificate.setLastUpdateDate(LocalDateTime
                .of(2021, 1, 1, 17, 0, 0));
        giftCertificate.setTags(new ArrayList<>());

        assertThrows(DataIntegrityViolationException.class, () -> giftCertificateDao.update(giftCertificate));
    }

    @Test
    void removeCorrectIdShouldNotThrowException() {
        assertDoesNotThrow(() -> giftCertificateDao.remove(1L));
    }

    @Test
    void removeTagHasGiftCertificateCorrectIdShouldNotThrowException() {
        assertDoesNotThrow(() -> giftCertificateDao.removeTagHasGiftCertificate(1L));
    }
}
