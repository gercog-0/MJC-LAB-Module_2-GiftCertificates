package com.epam.esm.impl.mapper;


import com.epam.esm.dao.api.entity.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.esm.impl.dao.SqlColumnName.TAG_ID;
import static com.epam.esm.impl.dao.SqlColumnName.TAG_NAME;

@Component
public class TagMapper implements RowMapper<Tag> {

    @Override
    public Tag mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Tag(
                resultSet.getLong(TAG_ID),
                resultSet.getString(TAG_NAME)
        );
    }
}
