package com.epam.esm.service.impl;

import com.epam.esm.dao.api.TagDao;
import com.epam.esm.dao.api.entity.Pagination;
import com.epam.esm.dao.api.entity.Tag;
import com.epam.esm.service.api.TagService;
import com.epam.esm.service.api.dto.PaginationDto;
import com.epam.esm.service.api.dto.TagDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.impl.validator.BaseValidator;
import com.epam.esm.service.impl.validator.impl.PaginationDtoValidator;
import com.epam.esm.service.impl.validator.impl.TagValidatorImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.service.api.exception.ErrorCode.TAG_WITH_SUCH_NAME_NOT_EXIST;
import static com.epam.esm.service.api.exception.ErrorCode.TAG_WITH_SUCH_ID_NOT_EXIST;
import static com.epam.esm.service.api.exception.ErrorCode.TAG_ID_SPECIFIED_WHILE_CREATING;
import static com.epam.esm.service.api.exception.ErrorCode.TAG_WITH_SUCH_NAME_ALREADY_EXIST;
import static com.epam.esm.service.api.exception.ErrorCode.TAG_POPULAR_NOT_FOUND;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;
    private final BaseValidator<TagDto> tagValidator;
    private final BaseValidator<PaginationDto> paginationValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public TagServiceImpl(TagDao tagDao, TagValidatorImpl tagValidator,
                          PaginationDtoValidator paginationValidator, ModelMapper modelMapper) {
        this.tagDao = tagDao;
        this.tagValidator = tagValidator;
        this.paginationValidator = paginationValidator;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TagDto> findAll(PaginationDto paginationDto) {
        paginationValidator.validate(paginationDto);
        Pagination pagination = modelMapper.map(paginationDto, Pagination.class);
        return tagDao.findAll(pagination).stream()
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TagDto findById(long id) {
        return tagDao.findById(id).
                map(tag -> modelMapper.map(tag, TagDto.class))
                .orElseThrow(() -> new ServiceException(TAG_WITH_SUCH_ID_NOT_EXIST, String.valueOf(id)));
    }

    @Transactional
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

    @Transactional
    @Override
    public void remove(long id) {
        findById(id);
        tagDao.remove(id);
    }

    @Override
    public TagDto findByName(String name) {
        return tagDao.findByName(name).
                map(tag -> modelMapper.map(tag, TagDto.class))
                .orElseThrow(() -> new ServiceException(TAG_WITH_SUCH_NAME_NOT_EXIST, name));
    }

    public TagDto findMostPopular() {
        return tagDao.findMostPopular()
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .orElseThrow(() -> new ServiceException(TAG_POPULAR_NOT_FOUND));
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
