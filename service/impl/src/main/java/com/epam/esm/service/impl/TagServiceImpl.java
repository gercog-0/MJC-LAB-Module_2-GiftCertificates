package com.epam.esm.service.impl;

import com.epam.esm.dao.api.TagDao;
import com.epam.esm.dao.api.entity.Tag;
import com.epam.esm.service.api.TagService;
import com.epam.esm.service.api.dto.TagDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.BaseValidator;
import com.epam.esm.service.impl.validator.impl.TagValidatorImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.service.api.exception.ErrorCode.*;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;
    private final BaseValidator<TagDto> tagValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public TagServiceImpl(TagDao tagDao, TagValidatorImpl tagValidator, ModelMapper modelMapper) {
        this.tagDao = tagDao;
        this.tagValidator = tagValidator;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TagDto> findAll() {
        return tagDao.findAll().stream()
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TagDto findById(long id) {
        return tagDao.findById(id).
                map(tag -> modelMapper.map(tag, TagDto.class))
                .orElseThrow(() -> new ServiceException(TAG_WITH_SUCH_ID_NOT_EXIST, String.valueOf(id)));
    }

    @Override
    public TagDto add(TagDto tagDto) {
        Long specifiedId = tagDto.getId();
        if (specifiedId != null) {
            throw new ServiceException(TAG_ID_SPECIFIED_WHILE_CREATING, String.valueOf(specifiedId));
        }
        tagValidator.validate(tagDto);
        String tagDtoName = tagDto.getName();
        checkTagNameToUnique(tagDtoName);
        Tag tag = modelMapper.map(tagDto, Tag.class);
        Tag addedTag = tagDao.add(tag);
        return modelMapper.map(addedTag, TagDto.class);
    }

    @Override
    public void remove(long id) {
        findById(id);
        tagDao.remove(id);
    }

    @Override
    public List<TagDto> findTagsByGiftCertificateId(long giftCertificateId) {
        return tagDao.findTagsByGiftCertificateId(giftCertificateId).stream()
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TagDto findByName(String name) {
        return tagDao.findByName(name).
                map(tag -> modelMapper.map(tag, TagDto.class))
                .orElseThrow(() -> new ServiceException(TAG_WITH_SUCH_NAME_NOT_EXIST, name));
    }

    @Override
    public boolean isTagExist(String name) {
        return tagDao.findByName(name).isPresent();
    }

    private void checkTagNameToUnique(String tagName) {
        if (isTagExist(tagName)) {
            throw new ServiceException(TAG_WITH_SUCH_NAME_ALREADY_EXIST, tagName);
        }
    }
}
