package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.GiftCertificateDao;
import com.epam.esm.dao.api.entity.GiftCertificate;
import com.epam.esm.dao.api.entity.GiftCertificateQueryParameters;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.dao.configuration.DaoTestConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DaoTestConfiguration.class)
class GiftCertificateDaoImplTest {

    private final GiftCertificateDao giftCertificateDao;
    private static GiftCertificate firstGiftCertificate;
    private static GiftCertificate secondGiftCertificate;
    private static GiftCertificate thirdGiftCertificate;
    private static GiftCertificate fourGiftCertificate;
    private static Pagination pagination;

    @Autowired
    public GiftCertificateDaoImplTest(GiftCertificateDao giftCertificateDao) {
        this.giftCertificateDao = giftCertificateDao;
    }

    @BeforeAll
    static void setUp() {
        firstGiftCertificate = new GiftCertificate();
        firstGiftCertificate.setId(1L);
        firstGiftCertificate.setName("Spa");
        firstGiftCertificate.setDescription("Hot relax to you");
        firstGiftCertificate.setPrice(new BigDecimal("500"));
        firstGiftCertificate.setDuration(2);
        firstGiftCertificate.setCreateDate(LocalDateTime.of(2020, 9, 12, 15, 0, 0));
        firstGiftCertificate.setLastUpdateDate(LocalDateTime.of(2021, 1, 1, 17, 0, 0));
        secondGiftCertificate = new GiftCertificate();
        thirdGiftCertificate = new GiftCertificate();
        fourGiftCertificate = new GiftCertificate();
        pagination = new Pagination();
        pagination.setPageNumber(1);
        pagination.setSize(4);
    }

    @AfterAll
    static void tearDown() {
        firstGiftCertificate = null;
        secondGiftCertificate = null;
        thirdGiftCertificate = null;
        fourGiftCertificate = null;
        pagination = null;
    }

    @Transactional
    @Test
    void methodUpdateShouldReturnCorrectUpdatedGiftCertificate(){
        GiftCertificate updateGiftCertificate = new GiftCertificate();
        updateGiftCertificate = new GiftCertificate();
        updateGiftCertificate.setName("SpaNEW");
        updateGiftCertificate.setDescription("Hot relax to you");
        updateGiftCertificate.setPrice(new BigDecimal("500"));
        updateGiftCertificate.setDuration(2);
        updateGiftCertificate.setCreateDate(LocalDateTime.of(2020, 9, 12, 15, 0, 0));
        updateGiftCertificate.setLastUpdateDate(LocalDateTime.of(2021, 1, 1, 17, 0, 0));

        GiftCertificate actualGiftCertificate = giftCertificateDao.update(updateGiftCertificate);

        assertEquals(updateGiftCertificate, actualGiftCertificate);
    }

    @Transactional
    @Test
    void methodAddShouldReturnCorrectGiftCertificate(){
        GiftCertificate expectedGiftCertificate = new GiftCertificate();
        expectedGiftCertificate.setId(5L);
        expectedGiftCertificate.setName("New");
        expectedGiftCertificate.setDescription("NEW");
        expectedGiftCertificate.setPrice(new BigDecimal("5000"));
        expectedGiftCertificate.setDuration(3);
        expectedGiftCertificate.setCreateDate(LocalDateTime.of(2020, 9, 12, 15, 0, 0));
        expectedGiftCertificate.setLastUpdateDate(LocalDateTime.of(2021, 1, 1, 17, 0, 0));
        GiftCertificate addedGiftCertificate = new GiftCertificate();
        addedGiftCertificate.setName("New");
        addedGiftCertificate.setDescription("NEW");
        addedGiftCertificate.setPrice(new BigDecimal("5000"));
        addedGiftCertificate.setDuration(3);
        addedGiftCertificate.setCreateDate(LocalDateTime.of(2020, 9, 12, 15, 0, 0));
        addedGiftCertificate.setLastUpdateDate(LocalDateTime.of(2021, 1, 1, 17, 0, 0));

        GiftCertificate actualGiftCertificate = giftCertificateDao.add(addedGiftCertificate);

        assertEquals(actualGiftCertificate, expectedGiftCertificate);
    }

    @Test
    void methodFindAllWithoutAdditionalParametersShouldReturnCorrectSizeGiftCertificates(){
        int expectedSize = 4;

        int actualSize = giftCertificateDao.findAll(new GiftCertificateQueryParameters(),pagination).size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void methodFindAllWithoutAdditionalParametersShouldReturnIncorrectSizeGiftCertificates(){
        int expectedSize = 3;

        int actualSize = giftCertificateDao.findAll(new GiftCertificateQueryParameters(),pagination).size();

        assertNotEquals(expectedSize, actualSize);
    }

    @Test
    void methodFindByIdShouldReturnCorrectOptionalGitCertificate(){
        long idToSearch = 1L;

        Optional<GiftCertificate> actualGiftCertificate = giftCertificateDao.findById(idToSearch);


        assertEquals(actualGiftCertificate.get().getName(), firstGiftCertificate.getName());
    }
}
