package com.netcreker.meetup.databasemanager;

import java.util.List;
import java.util.Map;

public interface DatabaseManager {
    //String getParameter(int id, String attrName);
    Map<String, String> getParameters(int id, List<String> attrNames);
}
