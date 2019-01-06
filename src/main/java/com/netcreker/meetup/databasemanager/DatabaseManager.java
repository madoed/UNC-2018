package com.netcreker.meetup.databasemanager;

import java.util.List;
import java.util.Map;

public interface DatabaseManager {

    long create(long objType, Map<Long, String> values, List<Map<Long, Long>> refs);

    void delete(long id);

    void update(long id, Map<Long, String> values, List<Map<Long, Long>> refs);

    void deleteReference(long id, long attrId, long ref);

    void deleteReferences(long id);

    void deleteValue(long id, long attrId);

    void deleteValues(long id);

    long getEntityByName(String name);

    long getReference(long id, long attrId);

    List<Long> getReferences(long id, long attrId);

    List<Map<String, Object>> getValue(long id, long attrId);

    List<Map<String, Object>> getValues(long id);

    void setName(long id, String name);

    void setReference(long id, long attrId, long ref);

    void setReferences(long id, List<Map<Long, Long>> refs);

    void setValue(long id, long attrId, String val);

    void setValues(long id, Map<Long, String> values);

}
