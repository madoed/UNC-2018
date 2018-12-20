package com.netcreker.meetup.databasemanager;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;
import java.text.SimpleDateFormat;

@Data
@Component
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class DatabaseManagerImpl implements DatabaseManager {

    // TODO : config date format
    // TODO : update query - fix multiple insertions

    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getValue(long id, long attrId) {
        String sql = String.format(
                "select * from Params" +
                " where object_id = %d and attr_id = %d",
                id, attrId);
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getValues(long id) {
        String sql = String.format(
                "select * from Params where object_id = %d",
                id);
        return jdbcTemplate.queryForList(sql);
    }

    public void setValue(long id, long attrId, String val) {
        String sql = String.format(
                "insert into Params (object_id, attr_id, value) values" +
                " (%d, %d, '%s')",
                id, attrId, val);
        jdbcTemplate.update(sql);
    }

    public void setValues(long id, Map<Long, String> values) {
        String sql = "insert into Params (object_id, attr_id, value) values";
        Iterator<Map.Entry<Long, String>> iter = values.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Long, String> value = iter.next();
            sql += String.format(" (%d, %d, '%s')",
                    id, value.getKey(), value.getValue());
            if (iter.hasNext()) sql += ",";
        }
        jdbcTemplate.update(sql);
    }

    public long getReference(long id, long attrId) {
        String sql = String.format(
                "select reference from Refs" +
                " where object_id = %d and attr_id = %d",
                id, attrId);
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    // TODO : test getReferences
    // TODO : set const ids for attrs
    public List<Long> getReferences(long id, long attrId) {
        String sql = String.format(
                "select reference from Refs" +
                " where object_id = %d and attr_id = %d",
                id, attrId);
        List<?> list = jdbcTemplate.queryForObject(sql, ArrayList.class);
        return null;
    }

    public void setReference(long id, long attrId, long ref) {
        String sql = String.format(
                "insert into Refs (object_id, attr_id, reference) values" +
                " (%d, %d, '%s')",
                id, attrId, ref);
        jdbcTemplate.update(sql);
    }

    public void delete(long id) {
        String query = "delete from Objects where object_id = " + id;
        jdbcTemplate.update(query);
    }

    /*
    public void create(String objectType, Map<String, String> parameters) {
        String objName = objectType + " " +
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                        .format(new Date());
        String query = String.format(
                "insert into meetupdb.Objects (object_type_id, object_name)" +
                " select object_type_id, '%s'" +
                " from meetupdb.Obj_types" +
                " where name like '%s'" +
                " returning object_id",
                objName, objectType);
        ResultSet result = executeQuery(query);
        try {
            result.next();
            setParameters(result.getLong("object_id"), parameters);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    private ResultSet executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private ResultSet executeUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private List<String> getStringResults(ResultSet result, String columnLabel) {
        List<String> values = new LinkedList<>();
        try {
            while (result.next())
                values.add(result.getString(columnLabel));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return values;
    }

    private Map<String, String> getAttrValues(ResultSet result, String columnLabel) {
        Map<String, String> params = new HashMap<>();
        try {
            while (result.next())
                params.put(result.getString("attr_name"),
                        result.getString(columnLabel));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return params;
    }*/
}
