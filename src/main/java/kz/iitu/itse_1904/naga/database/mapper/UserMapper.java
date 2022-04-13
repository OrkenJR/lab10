package kz.iitu.itse_1904.naga.database.mapper;

import kz.iitu.itse_1904.naga.database.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getLong("ID"))
                .firstName(rs.getString("FIRST_NAME"))
                .lastName("LAST_NAME")
                .username(rs.getString("USERNAME"))
                .email(rs.getString("EMAIL"))
                .phone(rs.getString("PHONE"))
                .password(rs.getString("PASSWORD"))
                .build();
    }
}
