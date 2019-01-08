package com.netcreker.meetup.entity;

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
    public String toString() {
        return "Id: " + id + ", name: " + name;
    }
}
