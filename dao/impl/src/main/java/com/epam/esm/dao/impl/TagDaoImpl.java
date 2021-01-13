package com.epam.esm.dao.impl;


import com.epam.esm.dao.api.TagDao;
import com.epam.esm.dao.api.entity.Tag;
import com.epam.esm.dao.impl.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.dao.impl.util.SqlQuery.*;

@Repository
public class TagDaoImpl implements TagDao {

    private static final int ZERO = 0;

    private final JdbcTemplate jdbcTemplate;
    private final TagMapper tagMapper;

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate, TagMapper tagMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.tagMapper = tagMapper;
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(TAG_FIND_ALL, tagMapper);
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return jdbcTemplate.query(TAG_FIND_BY_ID, new Object[]{id}, tagMapper)
                .stream().findAny();
    }

    @Override
    public Tag add(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(TAG_ADD,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tag.getName());
            return preparedStatement;
        }, keyHolder);
        Number key = keyHolder.getKey();
        if (key != null) {
            tag.setId(key.longValue());
        }
        return tag;
    }

    @Override
    public boolean update(Tag tag) {
        throw new UnsupportedOperationException("Method update for Tag is unsupported.");
    }

    @Transactional
    @Override
    public boolean remove(Long id) {
        removeTagHasGiftCertificate(id);
        return jdbcTemplate.update(TAG_REMOVE, id) > ZERO;
    }

    @Override
    public void removeTagHasGiftCertificate(Long tagId) {
        jdbcTemplate.update(REMOVE_TAG_HAS_GIFT_CERTIFICATE_BY_TAG, tagId);
    }

    @Override
    public List<Tag> findTagsByGiftCertificateId(Long giftCertificateId) {
        return jdbcTemplate.query(FIND_TAGS_BY_GIFT_CERTIFICATE_ID, new Object[]{giftCertificateId}, tagMapper);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return jdbcTemplate.query(TAG_FIND_BY_NAME, new Object[]{name}, tagMapper)
                .stream().findAny();
    }
}
