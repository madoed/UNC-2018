package com.netcreker.meetup.databasemanager;

import java.util.Map;

public interface DatabaseManager {

    String getValue(long id, long attr_id);

    Map<String, String> getValues(Long id);

    String setValue(String val, long id, long attr_id);

    String getReference(long id, long attr_id);

    String setReference(String val, long id, long attr_id);

    String getName(long id);

    String setName(String val, long id, long attr_id);

    void delete(long id);
}
