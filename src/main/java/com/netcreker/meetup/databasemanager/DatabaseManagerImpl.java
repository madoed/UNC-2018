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
    // TODO : set const ids for attrs & rewrite id generator
    // TODO : log operations

    private final JdbcTemplate jdbcTemplate;


    public long create(long objType, Map<Long, String> values, Map<Long, Long> refs)
            throws DatabaseManagerException {
        String objName = "obj_type " + objType + " " +
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                        .format(new Date());
        String sql = String.format(
                "insert into Objects (object_type_id, object_name) values" +
                        " (%d, '%s') returning object_id",
                objType, objName);

        long id = jdbcTemplate.queryForObject(sql, Long.class);
        setValues(id, values);
        setReferences(id, refs);

        return id;
    }


    public void delete(long id) {
        String sql = "delete from Objects where object_id = " + id;
        jdbcTemplate.update(sql);
    }


    public void deleteReference(long id, long attrId, long ref) {
        String sql = String.format(
                "delete from Refs where object_id = %d and" +
                        "attr_id = %d and reference = %d",
                id, attrId, ref);
        jdbcTemplate.update(sql);
    }


    public void deleteReferences(long id) {
        String sql = "delete from Refs where object_id = " + id;
        jdbcTemplate.update(sql);
    }


    public void deleteValues(long id) {
        String sql = "delete from Params where object_id = " + id;
        jdbcTemplate.update(sql);
    }


    public long getReference(long id, long attrId) {
        String sql = String.format(
                "select reference from Refs" +
                        " where object_id = %d and attr_id = %d",
                id, attrId);
        return jdbcTemplate.queryForObject(sql, Long.class);
    }


    public List<Long> getReferences(long id, long attrId) {
        String sql = String.format(
                "select reference from Refs" +
                        " where object_id = %d and attr_id = %d",
                id, attrId);
        return jdbcTemplate.queryForList(sql, Long.class);
    }


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



    public void setReference(long id, long attrId, long ref) {
        String sql = String.format(
                "insert into Refs (object_id, attr_id, reference) values" +
                        " (%d, %d, '%s')",
                id, attrId, ref);
        jdbcTemplate.update(sql);
    }


    public void setReferences(long id, Map<Long, Long> refs)
            throws DatabaseManagerException {
        if (refs == null)
            throw new DatabaseManagerException("References must be specified");
        if (refs.size() == 0)
            return;

        String sql = "insert into Refs (object_id, attr_id, reference) values";
        Iterator<Map.Entry<Long, Long>> iter = refs.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Long, Long> ref = iter.next();
            sql += String.format(" (%d, %d, %d)",
                    id, ref.getKey(), ref.getValue());
            if (iter.hasNext()) sql += ",";
        }

        jdbcTemplate.update(sql);
    }


    public void setValue(long id, long attrId, String val) {
        String sql = String.format(
                "insert into Params (object_id, attr_id, value) values" +
                    " (%d, %d, '%s')",
                id, attrId, val);
        jdbcTemplate.update(sql);
    }


    public void setValues(long id, Map<Long, String> values)
            throws DatabaseManagerException {
        if (values == null)
            throw new DatabaseManagerException("Values must be specified");
        if (values.size() == 0)
            return;

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


    // TODO : entity update function in database?

    public void update(long id, Map<Long, String> values, Map<Long, Long> refs)
            throws DatabaseManagerException {
        deleteReferences(id);
        deleteValues(id);

        setReferences(id, refs);
        setValues(id, values);
    }

}
