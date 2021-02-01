package com.epam.esm.dao.impl;

import com.epam.esm.dao.api.UserDao;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.dao.api.entity.User;
import com.epam.esm.dao.configuration.TestConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
class UserDaoImplTest {

    private final UserDao userDao;
    private static User firstUser;
    private static User secondUser;
    private static User thirdUser;
    private static Pagination pagination;

    @Autowired
    public UserDaoImplTest(UserDao userDao) {
        this.userDao = userDao;
    }

    @BeforeAll
    static void setUp() {
        firstUser = new User();
        firstUser.setId(1L);
        firstUser.setName("Ivan");
        secondUser = new User();
        secondUser.setId(2L);
        secondUser.setName("Vladislav");
        thirdUser = new User();
        thirdUser.setId(3L);
        thirdUser.setName("Gleb");
        pagination = new Pagination();
        pagination.setPageNumber(1);
        pagination.setSize(3);
    }

    @AfterAll
    static void tearDown() {
        firstUser = null;
        secondUser = null;
        thirdUser = null;
    }

    @Test
    void methodFindByIdShouldReturnUserOptional(){
        long idToFound = firstUser.getId();

        Optional<User> actualUser = userDao.findById(idToFound);

        assertEquals(Optional.of(firstUser), actualUser);
    }

    @Test
    void methodFindByIdShouldReturnEmptyOptional(){
        long idToFound = -1L;

        Optional<User> actualUser = userDao.findById(idToFound);

        assertEquals(actualUser, Optional.empty());
    }

    @Test
    void findAllShouldReturnCorrectSizeOfUsersInDatabase(){
        int expectedSize = 3;

        int actualSize = userDao.findAll(pagination).size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void methodUpdateShouldThrowException() {
        assertThrows(UnsupportedOperationException.class, () -> userDao.update(firstUser));
    }

    @Test
    void methodAddShouldThrowException() {
        assertThrows(UnsupportedOperationException.class, () -> userDao.add(firstUser));
    }

    @Test
    void methodRemoveShouldThrowException() {
        Long idToDelete = firstUser.getId();

        assertThrows(UnsupportedOperationException.class, () -> userDao.remove(idToDelete));
    }

}
