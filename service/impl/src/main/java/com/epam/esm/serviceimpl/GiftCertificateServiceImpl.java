package com.epam.esm.serviceimpl;

import com.epam.esm.daoapi.GiftCertificateDao;
import com.epam.esm.daoapi.entity.GiftCertificate;
import com.epam.esm.exception.ServiceErrorInformation;
import com.epam.esm.exception.ServiceException;
import com.epam.esm.serviceapi.dto.GiftCertificateDto;
import com.epam.esm.validator.GiftCertificateValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GiftCertificateServiceImpl implements com.epam.esm.serviceapi.GiftCertificateService {

    private final GiftCertificateDao giftCertificateDao;
    private final ModelMapper modelMapper;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, ModelMapper modelMapper) {
        this.giftCertificateDao = giftCertificateDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GiftCertificateDto> findAll() {
        List<GiftCertificate> foundGiftCertificates = giftCertificateDao.findAll();
        return foundGiftCertificates.stream()
                .map(giftCertificate -> modelMapper.map(giftCertificate, GiftCertificateDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public GiftCertificateDto findById(String id) {
        if (!GiftCertificateValidator.isIdCorrect(id)) {
            throw new ServiceException(ServiceErrorInformation.GIFT_CERTIFICATE_ID_INCORRECT);
        }
        long longId = Long.parseLong(id);
        Optional<GiftCertificate> foundGiftCertificate = giftCertificateDao.findById(longId);
        return foundGiftCertificate.map(giftCertificate -> modelMapper.map(giftCertificate, GiftCertificateDto.class))
                .orElseThrow(() -> new ServiceException(ServiceErrorInformation.GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST));
    }

    @Override
    public GiftCertificateDto add(GiftCertificateDto giftCertificateDto) {
        GiftCertificate giftCertificate = modelMapper.map(giftCertificateDto, GiftCertificate.class);
        GiftCertificateValidator.isGiftCertificateCorrect(giftCertificate);
        GiftCertificate addedGiftCertificate = giftCertificateDao.add(giftCertificate);
        return modelMapper.map(addedGiftCertificate, GiftCertificateDto.class);
    }


    @Override
    public void remove(String id) {
        if (!GiftCertificateValidator.isIdCorrect(id)) {
            throw new ServiceException(ServiceErrorInformation.GIFT_CERTIFICATE_ID_INCORRECT);
        }
        long longId = Long.parseLong(id);
        boolean isRemoved = giftCertificateDao.remove(longId);
        if (!isRemoved) {
            throw new ServiceException(ServiceErrorInformation.GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST);
        }
    }

    @Override
    public GiftCertificateDto update(GiftCertificateDto giftCertificateDto) {
        GiftCertificate giftCertificate = modelMapper.map(giftCertificateDto, GiftCertificate.class);
        GiftCertificateValidator.isGiftCertificateCorrect(giftCertificate);
        boolean isUpdated = giftCertificateDao.update(giftCertificate);
        if (!isUpdated) {
            throw new ServiceException(ServiceErrorInformation.GIFT_CERTIFICATE_WITH_SUCH_ID_NOT_EXIST);
        }
        return giftCertificateDto;
    }
}
