package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.databasemanager.DatabaseManager;
import com.netcreker.meetup.databasemanager.EAVDatabaseManager;
import com.netcreker.meetup.model.Schedule;

import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;
import java.sql.*;

@Log4j
public class EntityManagerTest {
    @Resource
    Connection connection = null;
    @Resource
    DatabaseManager dbm = null;
    @Resource
    EntityManager em = null;

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
            em = new EAVEntityManager(dbm);
            log.debug("EntityManager создан");
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
                em = null;
            } catch (SQLException ex) {
                log.error(null, ex);
            }
        }
    }

    @Test
    public void testLoad() {
        // В моей БД лежит объект типа schedule с аттрибутами:
        // id = 1;
        // name = "first schedule";
        Schedule schedule = em.load(1, Schedule.class);

        assertEquals(1, schedule.getId());
        assertEquals("first schedule", schedule.getName());
    }

    @Test
    public void testDelete() {
        // TODO : test delete
        //em.delete(2);
    }
}
