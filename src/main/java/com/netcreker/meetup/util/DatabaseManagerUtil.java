package com.netcreker.meetup.util;

import java.util.Iterator;

public class DatabaseManagerUtil {
    public static String paramSelect(long id, String attrName) {
        return String.format(paramSelectTemplate(),
                id, singleQuoted(attrName));
    }

    public static String paramSelect(long id, Iterable<String> attrNames) {
        return String.format(paramSelectTemplate(),
                id, singleQuoted(attrNames));
    }

    public static String paramInsert(long id, String attrName, String value) {
        return String.format(paramInsertTemplate(),
                id, value, attrName);
    }

    private static String paramSelectTemplate() {
        return "select * from Params natural join Attributes" +
                " where object_id = %d" +
                " and attr_name in (%s)";
    }

    // TODO : add obj_type-attr checks
    // TODO : add unique constraint for (object_id, attr_id) in Params
    private static String paramInsertTemplate() {
        return "insert into Params (object_id, attr_id, value)" +
                " select %d, attr_id, '%s'" +
                " from Attributes where attr_name like '%s'" +
                " on conflict (object_id, attr_id) do update" +
                " set value = excluded.value";
    }

    private static String singleQuoted(String input) {
        return "'" + input + "'";
    }

    private static String singleQuoted(Iterable<String> input) {
        StringBuilder sb = new StringBuilder();
        for (Iterator<String> iter = input.iterator(); iter.hasNext(); ) {
            sb.append("'"); sb.append(iter.next()); sb.append("'");
            if (iter.hasNext()) sb.append(",");
        }
        return sb.toString();
    }
}
