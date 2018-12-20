package com.netcreker.meetup.databasemanager;

import java.util.List;
import java.util.Map;

public interface DatabaseManager {

    long create(long objType, Map<Long, String> values, Map<Long, Long> refs)
            throws DatabaseManagerException;

    void delete(long id);

    void deleteReference(long id, long attrId, long ref);

    void deleteReferences(long id);

    void deleteValues(long id);

    long getReference(long id, long attrId);

    List<Long> getReferences(long id, long attrId);

    List<Map<String, Object>> getValue(long id, long attrId);

    List<Map<String, Object>> getValues(long id);

    void setReference(long id, long attrId, long ref);

    void setReferences(long id, Map<Long, Long> refs)
            throws DatabaseManagerException;

    void setValue(long id, long attrId, String val);

    void setValues(long id, Map<Long, String> values)
            throws DatabaseManagerException;

    void update(long id, Map<Long, String> values, Map<Long, Long> refs)
            throws DatabaseManagerException;

}
