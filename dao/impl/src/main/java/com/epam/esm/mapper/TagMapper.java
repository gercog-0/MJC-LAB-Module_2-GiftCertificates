package com.epam.esm.mapper;


import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.esm.dao.SqlColumnName.TAG_ID;
import static com.epam.esm.dao.SqlColumnName.TAG_NAME;

public class TagMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Tag(
                resultSet.getLong(TAG_ID),
                resultSet.getString(TAG_NAME)
        );
    }
}
