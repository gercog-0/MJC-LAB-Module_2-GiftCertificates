package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.TagDto;

import java.util.List;


public interface TagService extends BaseService<TagDto> {

    List<TagDto> findTagsByGiftCertificateId(long giftCertificateId);

    TagDto findByName(String name);

    boolean isTagExist(String name);
}
