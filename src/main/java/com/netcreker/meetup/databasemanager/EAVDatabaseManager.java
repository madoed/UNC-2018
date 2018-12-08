package com.netcreker.meetup.databasemanager;

import java.sql.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EAVDatabaseManager implements DatabaseManager {
    // TODO : config connection
    private Connection connection;

    public EAVDatabaseManager(Connection connection) {
        this.connection = connection;
    }

    public String getParameter(int id, String attrName) {
        // TODO : is this code legit?
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Params natural join Attributes" +
                            " where object_id = ?" +
                            " and attr_name like ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, attrName);

            ResultSet result = preparedStatement.executeQuery();
            result.next();
            return result.getString("value");
        }catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
        return null;
    }

    public Map<String, String> getParameters(int id, List<String> attrNames) {
        Map<String, String> values = new HashMap<>();
        for (String attrName : attrNames) {
            values.put(attrName, getParameter(id, attrName));
        }
        return values;
    }

    // TODO : upsert
    public void setParameter(int id, String attrName, String value) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Params (object_id, attr_id, value)" +
                            "SELECT ?, attr_id, ?" +
                            "FROM Attributes where attr_name like ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, value);
            preparedStatement.setString(3, attrName);
            ResultSet result = preparedStatement.executeQuery();
        }catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    public void setParameters(int id, Map<String, String> parameters) {
        Iterator<Map.Entry<String, String>> it = parameters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> parameter = it.next();
            setParameter(id, parameter.getKey(), parameter.getValue());
        }
    }

    // TODO : ADD DB CASCADE !!!
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Objects where object_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
        }catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
    }
}
