package com.netcreker.meetup.databasemanager.query;

public class ReferenceQuery extends Query {
    private long attrId;
    private long objectId;

    private ReferenceQuery() { }

    public static ReferenceQuery newInstance() {
        return new ReferenceQuery();
    }

    public ReferenceQuery attributeId(long id) {
        attrId = id;
        return this;
    }

    public ReferenceQuery objectId(long id) {
        objectId = id;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("select reference from refs");
        if (this.hasConditions()) {
            sb.append(" where");
            if (this.objectId != 0) {
                sb.append(" ").append(this.objectId);
            }
            if (this.attrId != 0) {
                sb.append(" ").append(this.attrId);
            }
        }
        return sb.toString();
    }

    private boolean hasConditions() {
        return !(objectId == 0 && attrId == 0);
    }
}
