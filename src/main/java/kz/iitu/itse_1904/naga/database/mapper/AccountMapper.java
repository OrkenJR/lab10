package kz.iitu.itse_1904.naga.database.mapper;

import kz.iitu.itse_1904.naga.database.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AccountMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Account.builder()
                .id(rs.getLong("ID"))
                .number(rs.getString("NUMBER"))
                .amount(rs.getLong("AMOUNT"))
                .isBlocked(rs.getBoolean("BLOCKED"))
                .build();
    }
}
