package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.TagDao;
import com.epam.esm.dao.api.entity.Tag;
import com.epam.esm.dao.impl.mapper.TagMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TagDaoTest {
    private TagDao tagDao;

    @BeforeEach
    void setUp() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("classpath:script/create_table_tag.sql")
                .addScript("classpath:script/fill_table_tag.sql")
                .addScript("classpath:script/create_table_tag_has_gift_certificate.sql")
                .addScript("classpath:script/fill_table_tag_has_gift_certificate.sql")
                .build();
        TagMapper tagMapper = new TagMapper();
        tagDao = new TagDaoImpl(new JdbcTemplate(dataSource), tagMapper);
    }

    @Order(1)
    @Test
    void findAllShouldReturnCorrectSizeListOfTags() {
        int expectedSize = 4;
        int actualSize = tagDao.findAll().size();
        assertEquals(expectedSize, actualSize);
    }

    @Order(2)
    @Test
    void addCorrectDataShouldReturnTag() {
        Tag tag = new Tag();
        tag.setId(5L);
        tag.setName("tagName");
        Tag actualTag = tagDao.add(tag);
        assertNotNull(actualTag);
    }

    @Order(3)
    @Test
    void addIncorrectDataShouldThrowException() {
        Tag tag = new Tag();
        tag.setId(5L);
        tag.setName("tagName incorrect because at least more than 45 symbols");
        assertThrows(DataIntegrityViolationException.class, () -> tagDao.add(tag));
    }

    @Order(4)
    @Test
    void updateShouldThrowException() {
        assertThrows(UnsupportedOperationException.class, () -> tagDao.update(new Tag()));
    }

    @Order(5)
    @Test
    void deleteCorrectIdShouldNotThrowException() {
        assertDoesNotThrow(() -> tagDao.remove(1L));
    }

    @Order(6)
    @Test
    void removeTagHasGiftCertificateShouldNotThrowException() {
        assertDoesNotThrow(() -> tagDao.removeTagHasGiftCertificate(1L));
    }

    @Order(7)
    @Test
    void findTagsByGiftCertificateIdCorrectDataShouldReturnListTags() {
        int expectedTagQuantity = 1;
        int actualTagQuantity = tagDao.findTagsByGiftCertificateId(1L).size();
        assertEquals(expectedTagQuantity, actualTagQuantity);
    }

    @Order(8)
    @Test
    void findTagsByGiftCertificateIdIncorrectDataShouldReturnEmptyList() {
        int actualTagQuantity = tagDao.findTagsByGiftCertificateId(5L).size();
        assertEquals(actualTagQuantity, 0);
    }

    @Order(9)
    @Test
    void findByNameCorrectNameShouldReturnTagOptional() {
        Tag expectedTag = new Tag();
        expectedTag.setId(2L);
        expectedTag.setName("relax");
        Optional<Tag> actualTag = tagDao.findByName("relax");
        assertEquals(Optional.of(expectedTag), actualTag);
    }

    @Order(10)
    @Test
    void findByNameIncorrectNameShouldReturnEmptyOptional() {
        Optional<Tag> actualTagOptional = tagDao.findByName("no name");
        assertEquals(Optional.empty(), actualTagOptional);
    }

    @AfterEach
    void tierDown() {
        new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("classpath:script/delete_table_tag.sql")
                .addScript("classpath:script/delete_table_tag_has_gift_certificate.sql")
                .build();
        tagDao = null;
    }
}
