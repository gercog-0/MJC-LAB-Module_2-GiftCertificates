package com.epam.esm.serviceimpl;

import com.epam.esm.daoapi.TagDao;
import com.epam.esm.daoapi.entity.Tag;
import com.epam.esm.exception.ServiceErrorInformation;
import com.epam.esm.exception.ServiceException;
import com.epam.esm.serviceapi.TagService;
import com.epam.esm.serviceapi.dto.TagDto;
import com.epam.esm.validator.TagValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;
    private final ModelMapper modelMapper;

    @Autowired
    public TagServiceImpl(TagDao tagDao, ModelMapper modelMapper) {
        this.tagDao = tagDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TagDto> findAll() {
        List<Tag> foundTags = tagDao.findAll();
        return foundTags.stream()
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TagDto findById(String id) {
        if (!TagValidator.isIdCorrect(id)) {
            throw new ServiceException(ServiceErrorInformation.TAG_ID_INCORRECT);
        }
        long longId = Long.parseLong(id);
        Optional<Tag> foundTag = tagDao.findById(longId);
        return foundTag.map(tag -> modelMapper.map(tag, TagDto.class))
                .orElseThrow(() -> new ServiceException(ServiceErrorInformation.TAG_WITH_SUCH_ID_NOT_EXIST));
    }

    @Override
    public TagDto add(TagDto tagDto) {
        Tag tag = modelMapper.map(tagDto, Tag.class);
        if (!TagValidator.isNameCorrect(tag.getName())) {
            throw new ServiceException(ServiceErrorInformation.TAG_NAME_INCORRECT);
        }
        Tag addedTag = tagDao.add(tag);
        return modelMapper.map(addedTag, TagDto.class);
    }


    @Override
    public void remove(String id) {
        if (!TagValidator.isIdCorrect(id)) {
            throw new ServiceException(ServiceErrorInformation.TAG_ID_INCORRECT);
        }
        long longId = Long.parseLong(id);
        boolean isRemoved = tagDao.remove(longId);
        if (!isRemoved) {
            throw new ServiceException(ServiceErrorInformation.TAG_WITH_SUCH_ID_NOT_EXIST);
        }
    }
}
