package com.epam.esm.dao;


import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.dao.SqlQuery.TAG_ADD;
import static com.epam.esm.dao.SqlQuery.TAG_FIND_ALL;
import static com.epam.esm.dao.SqlQuery.TAG_FIND_BY_ID;
import static com.epam.esm.dao.SqlQuery.TAG_REMOVE;

@Repository
public class TagDaoImpl implements TagDao {

    private final JdbcTemplate jdbcTemplate;
    private final TagMapper tagMapper = new TagMapper();

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(TAG_FIND_ALL, tagMapper);
    }

    @Override
    public Optional<Tag> findById(int id) {
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

    @Override
    public boolean remove(long id) {
        return jdbcTemplate.update(TAG_REMOVE, id) > 0;
    }
}
