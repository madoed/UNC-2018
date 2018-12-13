package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.databasemanager.DatabaseManager;
import com.netcreker.meetup.entity.check.Bill;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log
@Component
public class EntityManagerImpl implements EntityManager {

    @Autowired
    DatabaseManager databaseManager;


    @Override
    public Bill load() {
        return null;

    }
}
