package com.netcreker.meetup.databasemanager;

import lombok.extern.log4j.Log4j;

import java.sql.*;

import java.util.*;

import static com.netcreker.meetup.util.DatabaseManagerUtil.*;

@Log4j
public class EAVDatabaseManager implements DatabaseManager {
    // TODO : config connection
    private Connection connection;

    public EAVDatabaseManager(Connection connection) {
        this.connection = connection;
    }

    // TODO : consider pre-loading attrs and obj types
    public String getParameter(long id, String attrName) {
        String query = paramSelect(id, attrName);
        List<String> values = getStringResults(executeQuery(query), "value");
        try {
            if (values.size() > 1)
                throw new DatabaseManagerException("Query returned more than one result");
            return values.get(0);
        } catch (DatabaseManagerException dme) {
            log.error(dme.getMessage(), dme);
        }
        return null;
    }

    public Map<String, String> getParameters(long id, List<String> attrNames) {
        String query = paramSelect(id, attrNames);
        return getParameterValues(executeQuery(query), "value");
    }

    public void setParameter(long id, String value, String attrName) {
        String query = paramInsert(id, value, attrName);
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
        // TODO : generate object names
        String query = String.format(new StringBuilder()
                .append("insert into Objects (object_type_id, object_name)")
                .append(" values (%d, '%s') returning object_id")
                .toString(), getObjectTypeId(objectType), "noname");
        ResultSet result = executeQuery(query);
        try {
            result.next();
            setParameters(result.getLong("object_id"), parameters);
        } catch (SQLException sqlEx) {
            log.error(sqlEx.getMessage(), sqlEx);
        }
    }

    // TODO : ADD DB CASCADE !!!
    public void delete(long id) {
        String query = "delete from Objects where object_id = " + id;
        executeUpdate(query);
    }

    private ResultSet executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException sqlEx) {
            log.error(sqlEx.getMessage(), sqlEx);
        }
        return null;
    }

    // TODO : DEAL WITH ERROR - Object with id (n) does not exist !!!
    private ResultSet executeUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException sqlEx) {
            log.error(sqlEx.getMessage(), sqlEx);
        }
        return null;
    }

    private List<String> getStringResults(ResultSet result, String columnLabel) {
        List<String> values = new LinkedList<>();
        try {
            while (result.next())
                values.add(result.getString(columnLabel));
        } catch (SQLException sqlEx) {
            log.error(sqlEx.getMessage(), sqlEx);
        }
        return values;
    }

    private Map<String, String> getParameterValues(ResultSet result, String columnLabel) {
        Map<String, String> params = new HashMap<>();
        try {
            while (result.next())
                params.put(result.getString("attr_name"),
                        result.getString(columnLabel));
        } catch (SQLException sqlEx) {
            log.error(sqlEx.getMessage(), sqlEx);
        }
        return params;
    }

    private long getObjectTypeId(String objectType) {
        String query = String.format(new StringBuilder()
                .append("select object_type_id from Obj_types")
                .append(" where name like '%s'")
                .toString(), objectType);
        ResultSet result = executeQuery(query);
        try {
            if (!result.next())
                throw new DatabaseManagerException(
                        "An object type with name '" + objectType + "does not exist");
            return result.getLong("object_type_id");
        } catch (SQLException | DatabaseManagerException sqlEx) {
            log.error(sqlEx.getMessage(), sqlEx);
        }
        return -1;
    }
}
