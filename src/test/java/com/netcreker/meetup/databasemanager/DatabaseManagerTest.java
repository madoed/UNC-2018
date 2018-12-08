package com.netcreker.meetup.databasemanager;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;
import java.sql.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;
import java.util.ArrayList;

public class DatabaseManagerTest {
    @Resource
    Connection connection = null;

    @Before
    public void establishConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/unc2018";
            String name = "unc2018";
            String password = "unc2018";
            Class.forName("org.postgresql.Driver");
            System.out.println("Драйвер подключен");
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Соединение установлено");
        } catch (Exception ex) {
            Logger.getLogger(DatabaseManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseManagerTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testGetParameters() {
        List<String> attrNames = new ArrayList<>(5);
        attrNames.add("id");
        attrNames.add("name");
        attrNames.add("description");
        attrNames.add("privacy_setting");
        attrNames.add("user_id");

        DatabaseManager dbm = new EAVDatabaseManager(connection);
        Map<String, String> params = dbm.getParameters(1, attrNames);

        assertEquals("1", params.get("id"));
        assertEquals("first schedule", params.get("name"));
    }
}

