package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.annotations.*;
import com.netcreker.meetup.databasemanager.DatabaseManager;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.netcreker.meetup.util.EntityManagerUtil.*;


public class EAVEntityManager implements EntityManager {
    private DatabaseManager dbManager;

    public EAVEntityManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    // TODO : add NORMAL error handling
    public <T> T load(int id, Class<T> clazz) {
        try {
            List<Field> idFields = getFieldsWithAnnotation(clazz, Id.class);
            if (idFields.size() != 1)
                throw new EntityManagerException(
                        "Unable to resolve @Id annotation; encountered @Ids - "
                                + idFields.size());

            List<String> parameterAttrNames = getParameterAttrNames(clazz);
            Map<String, String> parameters = dbManager.getParameters(id,
                    parameterAttrNames);

            Object model = clazz.newInstance();
            setParameters(model, clazz, parameters);

            return (T) model;
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
        return null;
    }

    public void delete(int id) {
        // TODO : implement deletion logic
    }

    public void save(Object o, Class<?> clazz) {
        // TODO : implement saving logic
    }
}
