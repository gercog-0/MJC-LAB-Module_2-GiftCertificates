package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.TagDao;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.dao.api.entity.Tag;
import com.epam.esm.dao.configuration.TestConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
class TagDaoImplTest {

    private final TagDao tagDao;
    private static Tag firstTag;
    private static Tag secondTag;
    private static Tag thirdTag;
    private static Tag fourTag;
    private static Pagination pagination;
    private static Pagination incorrectPagination;

    @Autowired
    public TagDaoImplTest(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @BeforeAll
    static void setUp() {
        firstTag = new Tag();
        firstTag.setId(1L);
        firstTag.setName("chill");
        secondTag = new Tag();
        secondTag.setName("relax");
        thirdTag = new Tag();
        thirdTag.setName("work");
        fourTag = new Tag();
        fourTag.setName("funny");
        pagination = new Pagination();
        pagination.setPageNumber(1);
        pagination.setSize(4);
        incorrectPagination = new Pagination();
        incorrectPagination.setPageNumber(1);
        incorrectPagination.setSize(1);
    }

    @AfterAll
    static void tearDown() {
        firstTag = null;
        secondTag = null;
        thirdTag = null;
        fourTag = null;
        pagination = null;
    }

    @Test
    void methodFindMostPopularShouldReturnOptionalTag(){
        Optional<Tag> actualTag = tagDao.findMostPopular();

        assertEquals(actualTag, Optional.of(firstTag));
    }

    @Test
    void findByNameCorrectNameShouldReturnOptionalTag(){
        String nameToSearch = firstTag.getName();

        Optional<Tag> actualTag = tagDao.findByName(nameToSearch);

        assertEquals(actualTag, Optional.of(firstTag));
    }

    @Test
    void findByNameCorrectNameShouldReturnOptionalEmpty(){
        String nameToSearch = "unknown";

        Optional<Tag> actualTag = tagDao.findByName(nameToSearch);

        assertEquals(actualTag, Optional.empty());
    }

    @Test
    void methodFindByIdShouldReturnOptionalTag() {
        Optional<Tag> actualOptional = tagDao.findById(1L);

        assertEquals(actualOptional, Optional.of(firstTag));
    }

    @Test
    void methodFindByIdShouldReturnOptionalEmpty() {
        Optional<Tag> actualOptional = tagDao.findById(-1L);

        assertEquals(actualOptional, Optional.empty());
    }

    @Test
    void methodFindAllShouldReturnCorrectSizeTags() {
        int expectedSize = 4;

        int actualSize = tagDao.findAll(pagination).size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void methodFindAllShouldReturnIncorrectSizeTags() {
        int expectedSize = 4;

        int actualSize = tagDao.findAll(incorrectPagination).size();

        assertNotEquals(expectedSize, actualSize);
    }

    @Test
    void methodUpdateShouldThrowException() {
        assertThrows(UnsupportedOperationException.class, () -> tagDao.update(firstTag));
    }
}
