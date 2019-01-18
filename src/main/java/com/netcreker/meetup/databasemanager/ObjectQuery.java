package com.netcreker.meetup.databasemanager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ObjectQuery {
    private long objectTypeId;
    private String objectName;
    private final HashMap<Long, Set<Long>> references = new HashMap<>();
    private final HashMap<Long, Set<String>> values = new HashMap<>();

    private ObjectQuery() { }

    public static ObjectQuery newInstance() {
        return new ObjectQuery();
    }

    public void clear() {
        objectTypeId = 0;
        objectName = null;
        references.clear();
        values.clear();
    }

    public ObjectQuery objectTypeId(long id) {
        objectTypeId = id;
        return this;
    }

    public ObjectQuery objectName(String pattern) {
        objectName = pattern;
        return this;
    }

    public ObjectQuery reference(long attrId, long reference) {
        if (references.containsKey(attrId)) {
            references.get(attrId).add(reference);
        } else {
            Set<Long> refs = new HashSet<>();
            refs.add(reference);
            references.put(attrId, refs);
        }
        return this;
    }

    public ObjectQuery value(long attrId, String pattern) {
        if (values.containsKey(attrId)) {
            values.get(attrId).add(pattern);
        } else {
            Set<String> vals = new HashSet<>();
            vals.add(pattern);
            values.put(attrId, vals);
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("select object_id from Objects o");
        if (this.hasConditions()) {
            sb.append(" where");
            if (objectTypeId != 0) {
                sb.append(" object_type_id = " + objectTypeId);
                sb.append(" and");
            }
            if (objectName != null) {
                sb.append(" object_name like '" + objectName + "'");
                sb.append(" and");
            }
            for (Map.Entry<Long, Set<Long>> ref : references.entrySet()) {
                sb.append(" object_id in (select object_id from Refs where object_id = o.object_id" +
                        " and attr_id = " + ref.getKey() + " and (");
                for (Long reference : ref.getValue()) {
                    sb.append("reference = " + reference);
                    sb.append(" or ");
                }
                sb.replace(sb.lastIndexOf(" or "), sb.length(), "");
                sb.append("))");
                sb.append(" and");
            }
            for (Map.Entry<Long, Set<String>> val : values.entrySet()) {
                sb.append(" object_id in (select object_id from Params where object_id = o.object_id" +
                        " and attr_id = " + val.getKey() + " and (");
                for (String value : val.getValue()) {
                    sb.append("value like '" + value + "'");
                    sb.append(" or ");
                }
                sb.replace(sb.lastIndexOf(" or "), sb.length(), "");
                sb.append("))");
                sb.append(" and");
            }
            sb.replace(sb.lastIndexOf(" and"), sb.length(), "");
        }
        return sb.toString();
    }

    private boolean hasConditions() {
        return !(objectTypeId == 0 && objectName == null &&
                references.isEmpty() && values.isEmpty());
    }
}
