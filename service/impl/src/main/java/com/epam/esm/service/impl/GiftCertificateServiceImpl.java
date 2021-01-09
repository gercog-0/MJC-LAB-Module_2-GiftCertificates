package com.epam.esm.service.impl;

import com.epam.esm.dao.api.GiftCertificateDao;
import com.epam.esm.dao.api.entity.GiftCertificate;
import com.epam.esm.dao.api.entity.Tag;
import com.epam.esm.service.api.TagService;
import com.epam.esm.service.api.dto.TagDto;
import com.epam.esm.service.impl.exception.ServiceException;
import com.epam.esm.service.api.GiftCertificateService;
import com.epam.esm.service.api.dto.GiftCertificateDto;
import com.epam.esm.service.impl.validator.BaseValidator;
import com.epam.esm.service.impl.validator.impl.GiftCertificateValidatorImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.service.impl.exception.ErrorCode.GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final static int CHECK_NUMBER_TO_UPDATE = 0;

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
    public List<GiftCertificateDto> findAll() {
        return giftCertificateDao.findAll().stream()
                .map(this::mapAndSetTags)
                .collect(Collectors.toList());
    }

    @Override
    public GiftCertificateDto findById(long id) {
        return giftCertificateDao.findById(id)
                .map(this::mapAndSetTags)
                .orElseThrow(() -> new ServiceException(GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST, String.valueOf(id)));
    }

    @Transactional
    @Override
    public GiftCertificateDto add(GiftCertificateDto giftCertificateDto) {
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
    public GiftCertificateDto update(GiftCertificateDto giftCertificateDto) {
        GiftCertificateDto foundGiftCertificate = findById(giftCertificateDto.getId());
        setUpdatedFields(giftCertificateDto, foundGiftCertificate);
        validator.validate(foundGiftCertificate);
        resolveTags(foundGiftCertificate);
        giftCertificateDao.update(modelMapper.map(foundGiftCertificate, GiftCertificate.class));
        return foundGiftCertificate;
    }

    private void resolveTags(GiftCertificateDto giftCertificateDto) {
        List<TagDto> checkedTags = new ArrayList<>();
        List<TagDto> createdTags = giftCertificateDto.getTags();
        if (createdTags != null) {
            checkedTags = createdTags.stream()
                    .map(tagDto -> tagService.isTagExist(tagDto.getName()) ?
                            tagService.findByName(tagDto.getName()) : tagService.add(tagDto))
                    .collect(Collectors.toList());
        }
        giftCertificateDto.setTags(checkedTags);
    }

    private GiftCertificateDto mapAndSetTags(GiftCertificate giftCertificate) {
        GiftCertificateDto giftCertificateDto = modelMapper.map(giftCertificate, GiftCertificateDto.class);
        giftCertificateDto.setTags(tagService.findTagsByGiftCertificateId(giftCertificate.getId()));
        return giftCertificateDto;
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
        if (updatedGiftCertificate.getDuration() != CHECK_NUMBER_TO_UPDATE) {
            foundGiftCertificate.setDuration(updatedGiftCertificate.getDuration());
        }
        foundGiftCertificate.setLastUpdateDate(LocalDateTime.now());
    }
}
