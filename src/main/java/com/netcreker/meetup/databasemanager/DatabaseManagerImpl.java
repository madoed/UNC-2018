package com.netcreker.meetup.databasemanager;

import com.netcreker.meetup.databasemanager.query.Query;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Data
@Component
public class DatabaseManagerImpl implements DatabaseManager {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseManagerImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public long create(long objType) {
        String sql = "insert into Objects (object_type_id) values (" + objType + ") returning object_id";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public long create(long objType, String name) {
        if (name == null) return create(objType);
        String sql = "insert into Objects (object_type_id, object_name) values (" +
                objType + ", '" + name + "') returning object_id";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public void delete(long id) {
        String sql = "delete from Objects where object_id = " + id;
        jdbcTemplate.update(sql);
    }

    public void update(long id, @NonNull List<Map<Long, String>> values, @NonNull List<Map<Long, Long>> refs) {
        deleteAllReferences(id);
        deleteAllValues(id);
        setAllReferences(id, refs);
        setAllValues(id, values);
    }

    public void deleteReference(long id, long attrId, long ref) {
        String sql = String.format(
                "delete from Refs where object_id = %d and" +
                        "attr_id = %d and reference = %d",
                id, attrId, ref);
        jdbcTemplate.update(sql);
    }

    public void deleteAllReferences(long id) {
        String sql = "delete from Refs where object_id = " + id;
        jdbcTemplate.update(sql);
    }

    public void deleteAllValues(long id) {
        String sql = "delete from Params where object_id = " + id;
        jdbcTemplate.update(sql);
    }

    public List<Map<String, Object>> getAllReferences(long id) {
        String sql = "select attr_id, reference from Refs where object_id = " + id;
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getAllValues(long id) {
        String sql = "select * from Params where object_id = " + id;
        return jdbcTemplate.queryForList(sql);
    }

    public void setAllReferences(long id, @NonNull List<Map<Long, Long>> refs) {
        if (refs.size() == 0)
            return;

        StringBuilder sqlSb = new StringBuilder("insert into Refs (object_id, attr_id, reference) values");
        for (Iterator<Map<Long, Long>> ref = refs.iterator(); ref.hasNext(); ) {
            for (Iterator<Map.Entry<Long, Long>> iter = ref.next().entrySet().iterator(); iter.hasNext(); ) {
                Map.Entry<Long, Long> refEntry = iter.next();
                sqlSb.append(String.format(" (%d, %d, %d)",
                        id, refEntry.getKey(), refEntry.getValue()));
                if (iter.hasNext()) sqlSb.append(",");
            }
            if (ref.hasNext()) sqlSb.append(",");
        }

        jdbcTemplate.update(sqlSb.toString());
    }

    public void setAllValues(long id, @NonNull List<Map<Long, String>> values) {
        if (values.size() == 0)
            return;

        StringBuilder sqlSb = new StringBuilder("insert into Params (object_id, attr_id, value) values");
        for (Iterator<Map<Long, String>> val = values.iterator(); val.hasNext(); ) {
            for (Iterator<Map.Entry<Long, String>> iter = val.next().entrySet().iterator(); iter.hasNext(); ) {
                Map.Entry<Long, String> valEntry = iter.next();
                sqlSb.append(String.format(" (%d, %d, '%s')",
                        id, valEntry.getKey(), valEntry.getValue()));
                if (iter.hasNext()) sqlSb.append(",");
            }
            if (val.hasNext()) sqlSb.append(",");
        }

        jdbcTemplate.update(sqlSb.toString());
    }

    public String getName(long id) {
        String sql = "select object_name from Objects where object_id = " + id;
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public void setName(long id, @NonNull String name) {
        String sql = "update Objects set object_name = '" + name + "' where object_id = " + id;
        jdbcTemplate.update(sql);
    }

    public List<Long> queryForObjectIds(Query query) {
        return jdbcTemplate.queryForList(query.toString(), Long.class);
    }

    public List<Long> getEntitiesByName(@NonNull String name) {
        String sql = "select object_id from Objects where object_name like '" + name + "'";
        return jdbcTemplate.queryForList(sql, Long.class);
    }

    public void deleteValue(long id, long attrId) {
        String sql = "delete from Params where object_id = " + id +
                " and attr_id = " + attrId;
        jdbcTemplate.update(sql);
    }

    public List<Long> getReference(long id, long attrId) {
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

    public void setReference(long id, long attrId, long ref) {
        String sql = String.format(
                "insert into Refs (object_id, attr_id, reference) values" +
                        " (%d, %d, '%s')",
                id, attrId, ref);
        jdbcTemplate.update(sql);
    }

    public void setValue(long id, long attrId, @NonNull String val) {
        String sql = String.format(
                "insert into Params (object_id, attr_id, value) values" +
                    " (%d, %d, '%s')",
                id, attrId, val);
        jdbcTemplate.update(sql);
    }

}
