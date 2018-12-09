package com.netcreker.meetup.databasemanager;

import java.util.List;
import java.util.Map;

public interface DatabaseManager {
    String getParameter(long id, String attrName);
    Map<String, String> getParameters(long id, List<String> attrNames);

    void setParameter(long id, String attrName, String value);
    void setParameters(long id, Map<String, String> parameters);

    void create(String objectType, Map<String, String> parameters);

    void delete(long id);
}
