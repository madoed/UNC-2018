package com.netcreker.meetup.databasemanager;

import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;
import java.sql.*;
import java.util.Map;

import java.util.List;
import java.util.ArrayList;

import static com.netcreker.meetup.model.ModelGenerator.getScheduleParams;

// TODO : fix multiple logs
@Log4j
public class DatabaseManagerTest {
    @Resource
    Connection connection = null;
    @Resource
    DatabaseManager dbm = null;

    @Before
    public void establishConnection() {
        String url = "jdbc:postgresql://localhost:5432/meetupdb";
        String name = "unc2018";
        String password = "unc2018";
        try {
            Class.forName("org.postgresql.Driver");
            log.debug("Драйвер подключен");
            connection = DriverManager.getConnection(url, name, password);
            log.debug("Соединение установлено");
            dbm = new EAVDatabaseManager(connection);
            log.debug("DatabaseManager создан");
        } catch (Exception ex) {
            log.error(null, ex);
        }
    }

    @After
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                dbm = null;
            } catch (SQLException ex) {
                log.error(null, ex);
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

        Map<String, String> params = dbm.getParameters(1, attrNames);

        assertEquals("1", params.get("id"));
        assertEquals("first schedule", params.get("name"));
    }

    @Test
    public void testSetParameters() {
        Map<String, String> params = getScheduleParams();
        dbm.setParameters(2, params);
    }

    @Test
    public void testCreate() {
        Map<String, String> params = getScheduleParams();
        dbm.create("schedule", params);
    }

    @Test
    public void testDelete() {
        // PLease make sure the object with given id exists in the DB
        //dbm.delete(2);
    }
}

