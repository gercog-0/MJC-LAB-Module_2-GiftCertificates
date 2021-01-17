package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.TagDao;
import com.epam.esm.dao.api.entity.Tag;
import com.epam.esm.dao.impl.configuration.TestDatabaseConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestDatabaseConfiguration.class)
@ActiveProfiles("dev")
class TagDaoTest {

    private final TagDao tagDao;

    @Autowired
    public TagDaoTest(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Test
    void findAllShouldReturnCorrectSizeListOfTags() {
        int expectedSize = 5;

        int actualSize = tagDao.findAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void addCorrectDataShouldReturnTag() {
        Tag tag = new Tag();
        tag.setId(5L);
        tag.setName("tagName");

        Tag actualTag = tagDao.add(tag);

        assertNotNull(actualTag);
    }

    @Test
    void addIncorrectDataShouldThrowException() {
        Tag tag = new Tag();
        tag.setId(5L);
        tag.setName("tagName incorrect because at least more than 45 symbols");

        assertThrows(DataIntegrityViolationException.class, () -> tagDao.add(tag));
    }

    @Test
    void updateShouldThrowException() {
        assertThrows(UnsupportedOperationException.class, () -> tagDao.update(new Tag()));
    }

    @Test
    void deleteCorrectIdShouldNotThrowException() {
        assertDoesNotThrow(() -> tagDao.remove(1L));
    }

    @Test
    void removeTagHasGiftCertificateShouldNotThrowException() {
        assertDoesNotThrow(() -> tagDao.removeTagHasGiftCertificate(1L));
    }

    @Test
    void findTagsByGiftCertificateIdIncorrectDataShouldReturnEmptyList() {
        int actualTagQuantity = tagDao.findTagsByGiftCertificateId(5L).size();

        assertEquals(actualTagQuantity, 0);
    }

    @Test
    void findByNameCorrectNameShouldReturnTagOptional() {
        Tag expectedTag = new Tag();
        expectedTag.setId(2L);
        expectedTag.setName("relax");

        Optional<Tag> actualTag = tagDao.findByName("relax");

        assertEquals(Optional.of(expectedTag), actualTag);
    }

    @Test
    void findByNameIncorrectNameShouldReturnEmptyOptional() {
        Optional<Tag> actualTagOptional = tagDao.findByName("no name");

        assertEquals(Optional.empty(), actualTagOptional);
    }
}
