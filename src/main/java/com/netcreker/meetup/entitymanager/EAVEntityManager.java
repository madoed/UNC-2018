package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.annotations.*;
import com.netcreker.meetup.databasemanager.DatabaseManager;
import lombok.extern.log4j.Log4j;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.netcreker.meetup.util.EntityManagerUtil.*;

@Log4j
public class EAVEntityManager implements EntityManager {
    private DatabaseManager dbManager;

    public EAVEntityManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    // TODO : ADD REFERENCES
    // TODO : add nullability checks
    public <T> T load(long id, Class<T> clazz) {
        List<Field> idFields = getFieldsWithAnnotation(clazz, Id.class);
        try {
            if (idFields.size() != 1)
                throw new EntityManagerException(
                    "Unable to resolve @Id annotation; encountered zero or multiple @Ids");
        } catch (EntityManagerException e) {
            log.error(e.getMessage(), e);
        }
        List<String> parameterAttrNames = getParameterAttrNames(clazz);
        Map<String, String> parameters = dbManager.getParameters(id,
                parameterAttrNames);
        try {
            T model = clazz.newInstance();
            setParameters(model, clazz, parameters);
            setFieldValue(model, idFields.get(0), id);
            return model;
        } catch (InstantiationException
                | IllegalAccessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
        }

    public <T extends Object> void save(T instance) {
        //TODO : implement saving logic
    }

    public void delete(long id) {
        dbManager.delete(id);
    }
}
