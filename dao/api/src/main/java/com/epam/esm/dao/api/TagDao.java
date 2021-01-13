package com.epam.esm.dao.api;

import com.epam.esm.dao.api.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDao extends BaseDao<Tag> {

    List<Tag> findAll();

    List<Tag> findTagsByGiftCertificateId(Long giftCertificateId);

    Optional<Tag> findByName(String name);

    void removeTagHasGiftCertificate(Long tagId);
}
