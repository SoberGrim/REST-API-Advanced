package com.epam.esm.dao.mapper;

import com.epam.esm.config.DataSourceConfig;
import com.epam.esm.dao.constant.SqlTagQuery;
import com.epam.esm.dto.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class TagMapperTest {
    @Test
    public void mapRowTest() throws SQLException {
        List<Tag> expected = new ArrayList<>();
        expected.add(new Tag("#funny"));
        expected.add(new Tag("#cool"));
        expected.add(new Tag("#warm"));
        expected.add(new Tag("#cold"));
        expected.add(new Tag("#relax"));

        Statement statement = DataSourceConfig.dataSource.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(SqlTagQuery.SQL_SELECT_ALL_TAGS);

        List<Tag> actual = new ArrayList<>();
        while (rs.next()) {
            actual.add(new TagMapper().mapRow(rs, rs.getRow()));
        }
        assertEquals(expected, actual);
    }
}
