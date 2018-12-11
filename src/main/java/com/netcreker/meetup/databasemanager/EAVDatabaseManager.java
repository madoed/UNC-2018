package com.netcreker.meetup.databasemanager;

import lombok.extern.log4j.Log4j;

import java.sql.*;

import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import static com.netcreker.meetup.util.DatabaseManagerUtil.*;

@Log4j
public class EAVDatabaseManager implements DatabaseManager {
    // TODO : config date format
    // TODO : config db schema name
    // TODO : config connection
    private Connection connection;

    public EAVDatabaseManager(Connection connection) {
        this.connection = connection;
    }

    // TODO : consider pre-loading attrs and obj types
    public List<String> getParameter(long id, String attrName) {
        String query = paramSelect(id, attrName);
        return getStringResults(executeQuery(query), "value");
    }

    // TODO : ISSUE - ARRAY VALUES?
    public Map<String, String> getParameters(long id, List<String> attrNames) {
        String query = paramSelect(id, attrNames);
        return getAttrValues(executeQuery(query), "value");
    }

    public void setParameter(long id, String attrName, String value) {
        String query = paramInsert(id, attrName, value);
        executeUpdate(query);
    }

    // TODO : insert for multiple attributes?
    public void setParameters(long id, Map<String, String> parameters) {
        Iterator<Map.Entry<String, String>> it = parameters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> parameter = it.next();
            setParameter(id, parameter.getKey(), parameter.getValue());
        }
    }

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

    public void delete(long id) {
        String query = "delete from meetupdb.Objects where object_id = " + id;
        executeUpdate(query);
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
    }
}
