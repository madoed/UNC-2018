package com.netcreker.meetup.entity;

import java.util.Objects;

/*
** Generic entity class; all entities must derive from this
 */
public class Entity {

    protected long id;
    protected String name;

    public Entity() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity that = (Entity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Id: " + id + ", name: " + name;
    }
}
