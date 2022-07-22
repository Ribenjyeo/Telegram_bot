package ru.ribenjyeo.telegram_bot.dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//////////////////////////////////////////////////////////////
//  Маппер User для работы с БД и маппинга полей в таблице  //
//////////////////////////////////////////////////////////////

@Slf4j
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        var entity = new User (
                rs.getInt ("id"),
                rs.getString ("user_name"),
                rs.getString ("description")
        );
        log.trace ("mapRow(): entity = [{}]", entity);
        return entity;
    }
}