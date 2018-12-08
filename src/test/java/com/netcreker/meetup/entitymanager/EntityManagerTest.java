package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.databasemanager.DatabaseManager;
import com.netcreker.meetup.databasemanager.EAVDatabaseManager;
import com.netcreker.meetup.model.Schedule;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;
import java.sql.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.netcreker.meetup.model.ModelGenerator.getScheduleParams1;

public class EntityManagerTest {
    @Resource
    Connection connection = null;
    @Resource
    DatabaseManager dbm = null;

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
            dbm = new EAVDatabaseManager(connection);
            System.out.println("DatabaseManager подключен");
        } catch (Exception ex) {
            Logger.getLogger(EntityManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                dbm = null;
            } catch (SQLException ex) {
                Logger.getLogger(EntityManagerTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testLoad() {
        // В моей БД лежит объект типа schedule с аттрибутами:
        // id = 1;
        // name = "first schedule";
        EntityManager em = new EAVEntityManager(dbm);
        Schedule schedule = em.load(1, Schedule.class);

        assertEquals(1, schedule.getId());
        assertEquals("first schedule", schedule.getName());
    }
}
