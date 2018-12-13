package com.netcreker.meetup.databasemanager;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;


@Data
@Component
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class DatabaseManagerImpl implements DatabaseManager {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public String getValue(long id, long attr_id) {
        return null;
    }

    @Override
    public Map<String, String> getValues(Long id) {
        return null;
    }

    @Override
    public String setValue(String val, long id, long attr_id) {
        return null;
    }

    @Override
    public String getReference(long id, long attr_id) {
        return null;
    }

    @Override
    public String setReference(String val, long id, long attr_id) {
        return null;
    }

    @Override
    public String getName(long id) {
        return null;
    }

    @Override
    public String setName(String val, long id, long attr_id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }


}
