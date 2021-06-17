package com.epam.esm.dao.mapper;

import com.epam.esm.dao.constant.SqlTagColumnName;
import com.epam.esm.dto.Tag;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Tag mapper.
 */
@Component
public class TagMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong(SqlTagColumnName.TAG_ID);
        String name = rs.getString(SqlTagColumnName.TAG_NAME);
        return new Tag(id, name);
    }
}
