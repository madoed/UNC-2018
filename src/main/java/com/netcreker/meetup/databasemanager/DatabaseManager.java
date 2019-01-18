package com.netcreker.meetup.databasemanager;

import java.util.List;
import java.util.Map;

public interface DatabaseManager {

    long create(long objType);

    long create(long objType, String name);

    void delete(long id);

    void update(long id, List<Map<Long, String>> values, List<Map<Long, Long>> refs);

    void deleteAllReferences(long id);

    void deleteAllValues(long id);

    List<Map<String, Object>> getAllReferences(long id);

    List<Map<String, Object>> getAllValues(long id);

    void setAllReferences(long id, List<Map<Long, Long>> refs);

    void setAllValues(long id, List<Map<Long, String>> values);

    String getName(long id);

    void setName(long id, String name);

    List<Long> queryForObjectIds(ObjectQuery query);

    List<Long> getEntitiesByName(String name);

    void deleteReference(long id, long attrId, long ref);

    void deleteValue(long id, long attrId);

    List<Long> getReference(long id, long attrId);

    List<Map<String, Object>> getValue(long id, long attrId);

    void setReference(long id, long attrId, long ref);

    void setValue(long id, long attrId, String val);

}
