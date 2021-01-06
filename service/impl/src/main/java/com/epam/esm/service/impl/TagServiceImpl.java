package com.epam.esm.service.impl;

import com.epam.esm.dao.api.TagDao;
import com.epam.esm.dao.api.entity.Tag;
import com.epam.esm.service.impl.exception.ServiceException;
import com.epam.esm.service.impl.validator.impl.TagValidatorImpl;
import com.epam.esm.service.api.TagService;
import com.epam.esm.service.api.dto.TagDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.service.impl.exception.ErrorCode.TAG_WITH_SUCH_ID_NOT_EXIST_ERROR;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;
    private final TagValidatorImpl tagValidatorImpl;
    private final ModelMapper modelMapper;

    @Autowired
    public TagServiceImpl(TagDao tagDao, TagValidatorImpl tagValidatorImpl, ModelMapper modelMapper) {
        this.tagDao = tagDao;
        this.tagValidatorImpl = tagValidatorImpl;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TagDto> findAll() {
        return tagDao.findAll().stream()
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TagDto findById(Long id) {
        tagValidatorImpl.validateId(id);
        return tagDao.findById(id).
                map(tag -> modelMapper.map(tag, TagDto.class))
                .orElseThrow(() -> new ServiceException(TAG_WITH_SUCH_ID_NOT_EXIST_ERROR));
    }

    @Override
    public TagDto add(TagDto tagDto) {
        Tag tag = modelMapper.map(tagDto, Tag.class);
        tagValidatorImpl.validateName(tag.getName());
        Tag addedTag = tagDao.add(tag);
        return modelMapper.map(addedTag, TagDto.class);
    }

    @Override
    public void remove(Long id) {
        findById(id);
        tagDao.remove(id);
    }
}