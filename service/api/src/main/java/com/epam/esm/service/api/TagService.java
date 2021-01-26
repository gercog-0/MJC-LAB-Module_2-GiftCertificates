package com.epam.esm.service.api;

import com.epam.esm.service.api.dto.TagDto;

import java.util.List;


/**
 * The interface Tag service.
 */
public interface TagService extends BaseService<TagDto> {

    /**
     * Find all list.
     *
     * @return the list
     */
    List<TagDto> findAll();

    /**
     * Find by name tag dto.
     *
     * @param name the name
     * @return the tag dto
     */
    TagDto findByName(String name);

    /**
     * Is tag exist boolean.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isTagExist(String name);
}
