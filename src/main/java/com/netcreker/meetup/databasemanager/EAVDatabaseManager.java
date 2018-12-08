package com.netcreker.meetup.databasemanager;

import java.sql.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EAVDatabaseManager implements DatabaseManager {
    // TODO : config connection
    private Connection connection;

    public EAVDatabaseManager(Connection connection) {
        this.connection = connection;
    }

    public Map<String, String> getParameters(int id, List<String> attrNames) {
        Map<String, String> values = new HashMap<>();
        try {
            // TODO : is this code legit?
            for (String attrName : attrNames) {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM Params natural join Attributes" +
                                " where object_id = ?" +
                                " and attr_name like ?");
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, attrName);

                ResultSet result = preparedStatement.executeQuery();
                while (result.next()) {
                    values.put(attrName, result.getString("value"));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
        return values;
    }
}
