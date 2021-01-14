package com.epam.esm.dao.api;

import com.epam.esm.dao.api.entity.Tag;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@code TagDao} represents an extended abstract behavior
 * for working with the Tag entity.
 *
 * @author Ivan Yanushkevich
 * @version 1.0
 */
public interface TagDao extends BaseDao<Tag> {

    /**
     * Find all records in database.
     *
     * @return the list
     */
    List<Tag> findAll();

    /**
     * Find tags by gift certificate id.
     *
     * @param giftCertificateId the gift certificate id
     * @return the list
     */
    List<Tag> findTagsByGiftCertificateId(Long giftCertificateId);

    /**
     * Find optional of by name.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Tag> findByName(String name);

    /**
     * Remove records from cross table by tag id.
     *
     * @param tagId the tag id
     */
    void removeTagHasGiftCertificate(Long tagId);
}
