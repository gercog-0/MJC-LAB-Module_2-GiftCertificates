package com.epam.esm.service.impl;

import com.epam.esm.dao.api.GiftCertificateDao;
import com.epam.esm.dao.api.entity.GiftCertificate;
import com.epam.esm.dao.api.entity.GiftCertificateQueryParameters;
import com.epam.esm.service.api.TagService;
import com.epam.esm.service.api.dto.GiftCertificateQueryParametersDto;
import com.epam.esm.service.api.dto.TagDto;
import com.epam.esm.service.api.exception.ServiceException;
import com.epam.esm.service.api.GiftCertificateService;
import com.epam.esm.service.api.dto.GiftCertificateDto;
import com.epam.esm.service.impl.validator.BaseValidator;
import com.epam.esm.service.impl.validator.impl.GiftCertificateValidatorImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.service.api.exception.ErrorCode.GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST;
import static com.epam.esm.service.api.exception.ErrorCode.GIFT_CERTIFICATE_ID_SPECIFIED_WHILE_CREATING;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;
    private final TagService tagService;
    private final BaseValidator<GiftCertificateDto> validator;
    private final ModelMapper modelMapper;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, TagService tagService,
                                      GiftCertificateValidatorImpl validator, ModelMapper modelMapper) {
        this.giftCertificateDao = giftCertificateDao;
        this.tagService = tagService;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GiftCertificateDto> findAll(GiftCertificateQueryParametersDto giftCertificateQueryParametersDto) {
        GiftCertificateQueryParameters giftCertificateQueryParameters =
                modelMapper.map(giftCertificateQueryParametersDto, GiftCertificateQueryParameters.class);
        return giftCertificateDao.findAll(giftCertificateQueryParameters).stream()
                .map(this::mapToDtoAndSetTags)
                .collect(Collectors.toList());
    }

    @Override
    public GiftCertificateDto findById(long id) {
        return giftCertificateDao.findById(id)
                .map(this::mapToDtoAndSetTags)
                .orElseThrow(() -> new ServiceException(GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST, String.valueOf(id)));
    }

    @Transactional
    @Override
    public GiftCertificateDto add(GiftCertificateDto giftCertificateDto) {
        Long specifiedId = giftCertificateDto.getId();
        if (specifiedId != null) {
            throw new ServiceException(GIFT_CERTIFICATE_ID_SPECIFIED_WHILE_CREATING, String.valueOf(specifiedId));
        }
        giftCertificateDto.setCreateDate(LocalDateTime.now());
        giftCertificateDto.setLastUpdateDate(LocalDateTime.now());
        validator.validate(giftCertificateDto);
        resolveTags(giftCertificateDto);
        GiftCertificate giftCertificate = modelMapper.map(giftCertificateDto, GiftCertificate.class);
        GiftCertificate addedGiftCertificate = giftCertificateDao.add(giftCertificate);
        return modelMapper.map(addedGiftCertificate, GiftCertificateDto.class);
    }

    @Override
    public void remove(long id) {
        findById(id);
        giftCertificateDao.remove(id);
    }

    @Transactional
    @Override
    public GiftCertificateDto updatePart(GiftCertificateDto giftCertificateDto) {
        GiftCertificateDto foundGiftCertificate = findById(giftCertificateDto.getId());
        setUpdatedFields(giftCertificateDto, foundGiftCertificate);
        validator.validate(foundGiftCertificate);
        resolveTags(foundGiftCertificate);
        giftCertificateDao.update(modelMapper.map(foundGiftCertificate, GiftCertificate.class));
        return foundGiftCertificate;
    }

    @Override
    public GiftCertificateDto update(GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.setCreateDate(LocalDateTime.now());
        giftCertificateDto.setLastUpdateDate(LocalDateTime.now());
        validator.validate(giftCertificateDto);
        resolveTags(giftCertificateDto);
        giftCertificateDao.update(modelMapper.map(giftCertificateDto, GiftCertificate.class));
        return giftCertificateDto;
    }

    private void resolveTags(GiftCertificateDto giftCertificateDto) {
        List<TagDto> checkedTags = Collections.emptyList();
        List<TagDto> tags = giftCertificateDto.getTags();
        if (tags != null) {
            checkedTags = tags.stream()
                    .map(this::findOrCreateTagIfNotExist)
                    .collect(Collectors.toList());
        }
        giftCertificateDto.setTags(checkedTags);
    }

    private GiftCertificateDto mapToDtoAndSetTags(GiftCertificate giftCertificate) {
        GiftCertificateDto giftCertificateDto = modelMapper.map(giftCertificate, GiftCertificateDto.class);
        giftCertificateDto.setTags(tagService.findTagsByGiftCertificateId(giftCertificate.getId()));
        return giftCertificateDto;
    }

    private TagDto findOrCreateTagIfNotExist(TagDto tagDto) {
        String tagDtoName = tagDto.getName();
        return tagService.isTagExist(tagDtoName) ?
                tagService.findByName(tagDtoName) : tagService.add(tagDto);
    }

    private void setUpdatedFields(GiftCertificateDto updatedGiftCertificate,
                                  GiftCertificateDto foundGiftCertificate) {
        if (updatedGiftCertificate.getTags() != null) {
            foundGiftCertificate.setTags(updatedGiftCertificate.getTags());
        }
        if (updatedGiftCertificate.getName() != null) {
            foundGiftCertificate.setName(updatedGiftCertificate.getName());
        }
        if (updatedGiftCertificate.getDescription() != null) {
            foundGiftCertificate.setDescription(updatedGiftCertificate.getDescription());
        }
        if (updatedGiftCertificate.getPrice() != null) {
            foundGiftCertificate.setPrice(updatedGiftCertificate.getPrice());
        }
        if (updatedGiftCertificate.getDuration() != null) {
            foundGiftCertificate.setDuration(updatedGiftCertificate.getDuration());
        }
        foundGiftCertificate.setLastUpdateDate(LocalDateTime.now());
    }
}
