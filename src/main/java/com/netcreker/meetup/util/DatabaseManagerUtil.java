package com.netcreker.meetup.util;

import java.util.Iterator;
import java.util.Map;
import java.util.List;

public class DatabaseManagerUtil {
    /*
    private static final String ATTR_ID_SELECT_TEMPLATE =
            "select attr_name from meetupdb.Attributes where attr_name in (%s)";
            */

    private static final String PARAM_SELECT_TEMPLATE =
            "select * from meetupdb.Params natural join meetupdb.Attributes" +
                    " where object_id = %d" +
                    " and attr_name in (%s)";

    private static final String REF_SELECT_TEMPLATE =
            "select * from meetupdb.Refs natural join meetupdb.Attributes" +
                    " where object_id = %d" +
                    " and attr_name in (%s)";

    // TODO : add obj_type-attr checks
    private static final String PARAM_INSERT_TEMPLATE =
            "insert into meetupdb.Params (object_id, attr_id, value)" +
                    " select %d, attr_id, '%s'" +
                    " from meetupdb.Attributes where attr_name like '%s'";

    private static final String REF_INSERT_TEMPLATE =
            "insert into meetupdb.Refs (object_id, attr_id, reference)" +
                    " select %d, attr_id, %d" +
                    " from meetupdb.Attributes where attr_name like '%s'";
    /*
    private static final String PARAM_INSERT_TEMPLATE =
            "insert into meetupdb.Params (object_id, attr_id, value) values";

    private static final String VALUE_TEMPLATE =
            " (%d, %d, %s)";

    private static final String paramOnConflictTemplate =
            " on conflict (object_id, attr_id) do update set value = excluded.value";
            */

    /*
    public static String attrIdSelect(List<String> attrNames) {
        return String.format(ATTR_ID_SELECT_TEMPLATE,
                singleQuoted(attrNames));
    }
    */

    public static String paramSelect(long id, String attrName) {
        return String.format(PARAM_SELECT_TEMPLATE,
                id, singleQuoted(attrName));
    }

    public static String paramSelect(long id, Iterable<String> attrNames) {
        return String.format(PARAM_SELECT_TEMPLATE,
                id, singleQuoted(attrNames));
    }

    public static String paramInsert(long id, String attrName, String value) {
        return String.format(PARAM_INSERT_TEMPLATE,
                id, value, attrName);
    }

    /*
    public static String paramInsert(long id, long attrId, String value) {
        return PARAM_INSERT_TEMPLATE + formatValue(id, attrId, value);
    }

    private static String paramInsert(long id, Map<Integer, String> attrValues) {
        StringBuilder sb = new StringBuilder();
        sb.append(PARAM_INSERT_TEMPLATE);
        Iterator<Map.Entry<Integer, String>> it = attrValues.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> entry = it.next();
            sb.append(formatValue(id, entry.getKey(), entry.getValue()));
            if (it.hasNext()) sb.append(",");
        }
        return sb.toString();
    }
    */

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

    /*
    private static String formatValue(long id, long attrId, String value) {
        return String.format(VALUE_TEMPLATE, id, attrId, value);
    }
    */
}
