package com.epam.esm.service.impl;

import com.epam.esm.dao.api.GiftCertificateDao;
import com.epam.esm.dao.api.entity.GiftCertificate;
import com.epam.esm.service.impl.exception.ServiceException;
import com.epam.esm.service.api.GiftCertificateService;
import com.epam.esm.service.api.dto.GiftCertificateDto;
import com.epam.esm.service.impl.validator.impl.GiftCertificateValidatorImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.service.impl.exception.ErrorCode.GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST_ERROR;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;
    private final GiftCertificateValidatorImpl giftCertificateValidatorImpl;
    private final ModelMapper modelMapper;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao,
                                      GiftCertificateValidatorImpl giftCertificateValidatorImpl, ModelMapper modelMapper) {
        this.giftCertificateDao = giftCertificateDao;
        this.giftCertificateValidatorImpl = giftCertificateValidatorImpl;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        return giftCertificateDao.findAll().stream()
                .map(giftCertificate -> modelMapper.map(giftCertificate, GiftCertificateDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GiftCertificateDto findById(Long id) {
        giftCertificateValidatorImpl.validateId(id);
        return giftCertificateDao.findById(id)
                .map(giftCertificate -> modelMapper.map(giftCertificate, GiftCertificateDto.class))
                .orElseThrow(() -> new ServiceException(GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST_ERROR));
    }

    @Override
    public GiftCertificateDto add(GiftCertificateDto giftCertificateDto) {
        GiftCertificate giftCertificate = modelMapper.map(giftCertificateDto, GiftCertificate.class);
        giftCertificateValidatorImpl.isCorrect(giftCertificate);
        GiftCertificate addedGiftCertificate = giftCertificateDao.add(giftCertificate);
        return modelMapper.map(addedGiftCertificate, GiftCertificateDto.class);
    }

    @Override
    public void remove(Long id) {
        findById(id);
        giftCertificateDao.remove(id);
    }

    @Override
    public GiftCertificateDto update(GiftCertificateDto giftCertificateDto) {
        GiftCertificate giftCertificate = modelMapper.map(giftCertificateDto, GiftCertificate.class);
        giftCertificateValidatorImpl.isCorrect(giftCertificate);
        findById(giftCertificate.getId());
        giftCertificateDao.update(giftCertificate);
        return giftCertificateDto; // TODO: 06.01.2021
    }
}
