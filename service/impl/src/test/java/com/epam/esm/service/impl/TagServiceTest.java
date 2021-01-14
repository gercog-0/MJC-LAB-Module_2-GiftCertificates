package com.epam.esm.service.impl;

import com.epam.esm.dao.api.TagDao;
import com.epam.esm.dao.api.entity.Tag;
import com.epam.esm.service.api.TagService;
import com.epam.esm.service.api.dto.TagDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.impl.TagValidatorImpl;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TagServiceTest {

    private TagDao tagDao;
    private TagValidatorImpl validator;
    private ModelMapper modelMapper;
    private TagService tagService;

    @BeforeEach
    void setUp() {
        tagDao = mock(TagDao.class);
        validator = mock(TagValidatorImpl.class);
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        tagService = new TagServiceImpl(tagDao, validator, modelMapper);
    }

    @AfterEach
    void tierDown() {
        tagDao = null;
        validator = null;
        modelMapper = null;
        tagService = null;
    }

    @Test
    void findAllCorrectDataShouldReturnListWithTagsDto() {
        when(tagDao.findAll()).thenReturn(Arrays.asList(new Tag(1L, "nameFirst"),
                new Tag(2L, "nameSecond")));
        int expectedSize = 2;

        int actualSize = tagService.findAll().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void findByIdCorrectIdShouldReturnTagDto() {
        Tag tag = new Tag(1L, "foundTag");
        TagDto expectedTag = new TagDto(1L, "foundTag");
        when(tagDao.findById(any(Long.class))).thenReturn(Optional.of(tag));

        TagDto actualTag = tagService.findById(1L);

        assertEquals(expectedTag, actualTag);
    }

    @Test
    void findByIdNotExistIdShouldThrowException() {
        when(tagDao.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> tagService.findById(-1L));
    }

    @Test
    void addCorrectDataShouldReturnTagDto() {
        Tag tag = new Tag();
        tag.setName("nameTest");
        TagDto expectedTag = new TagDto();
        expectedTag.setName("nameTest");
        when(tagDao.add(any(Tag.class))).thenReturn(tag);
        when(tagDao.findByName(any(String.class))).thenReturn(Optional.empty());

        TagDto actualTag = tagService.add(expectedTag);

        assertEquals(expectedTag, actualTag);
    }


    @Test
    void addCreatedTagWithIdShouldThrowException() {
        TagDto expectedTag = new TagDto();
        expectedTag.setId(1L);
        expectedTag.setName("nameTest");

        assertThrows(ServiceException.class, () -> tagService.add(expectedTag));
    }

    @Test
    void addDuplicateShouldThrowException() {
        Tag tag = new Tag();
        tag.setName("nameTest");
        TagDto expectedTag = new TagDto();
        expectedTag.setName("nameTest");
        when(tagDao.add(any(Tag.class))).thenReturn(tag);
        when(tagDao.findByName(any(String.class))).thenReturn(Optional.of(tag));

        assertThrows(ServiceException.class, () -> tagService.add(expectedTag));
    }

    @Test
    void removeCorrectIdShouldNotThrowException() {
        Tag tag = new Tag(1L, "foundTag");
        when(tagDao.findById(any(Long.class))).thenReturn(Optional.of(tag));

        assertDoesNotThrow(() -> tagService.remove(1L));
    }

    @Test
    void removeNotFoundByIdShouldThrowException() {
        when(tagDao.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> tagService.remove(1L));
    }

    @Test
    void findTagsByGiftCertificateIdCorrectIdShouldReturnListWithTags() {
        Tag tag1 = new Tag();
        Tag tag2 = new Tag();
        tag2.setName("name test");
        TagDto tagDto1 = new TagDto();
        TagDto tagDto2 = new TagDto();
        tagDto2.setName("name test");
        List<TagDto> tagsDto = Arrays.asList(tagDto1, tagDto2);
        List<Tag> tags = Arrays.asList(tag1, tag2);
        when(tagDao.findTagsByGiftCertificateId(any(Long.class))).thenReturn(tags);

        List<TagDto> actualListWithTags = tagService.findTagsByGiftCertificateId(1L);

        assertEquals(tagsDto, actualListWithTags);
    }

    @Test
    void findByNameCorrectNameShouldReturnTagDto() {
        Tag tag = new Tag();
        tag.setName("name");
        TagDto expectedTag = new TagDto();
        expectedTag.setName("name");
        when(tagDao.findByName(any(String.class))).thenReturn(Optional.of(tag));

        TagDto actualTag = tagService.findByName("name");

        assertEquals(expectedTag, actualTag);
    }

    @Test
    void findByNameNotExistIdShouldThrowException() {
        when(tagDao.findByName(any(String.class))).thenReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> tagService.findByName("name"));
    }

    @Test
    void isTagExistCorrectNameShouldReturnTrue() {
        when(tagDao.findByName(any(String.class))).thenReturn(Optional.of(new Tag()));

        boolean condition = tagService.isTagExist("name");

        assertTrue(condition);
    }

    @Test
    void isTagExistIncorrectNameShouldReturnFalse() {
        when(tagDao.findByName(any(String.class))).thenReturn(Optional.empty());

        boolean condition = tagService.isTagExist("name");

        assertFalse(condition);
    }
}
