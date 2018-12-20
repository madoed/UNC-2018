package com.netcreker.meetup.databasemanager;

import java.util.List;
import java.util.Map;

public interface DatabaseManager {

    List<Map<String, Object>> getValue(long id, long attrId);

    List<Map<String, Object>> getValues(long id);

    void setValue(long id, long attrId, String val);

    void setValues(long id, Map<Long, String> values);

    long getReference(long id, long attrId);

    List<Long> getReferences(long id, long attrId);

    void setReference(long id, long attrId, long ref);

    //void deleteReference(long id, long attrId, long ref);

    void delete(long id);

    //long create(long objType, Map<Long, String> values, Map<Long, Long> refs);

}
