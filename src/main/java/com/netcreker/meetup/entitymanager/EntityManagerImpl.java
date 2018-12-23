package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.databasemanager.DatabaseManager;
import com.netcreker.meetup.databasemanager.DatabaseManagerException;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.util.Reflection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@Component
public class EntityManagerImpl implements EntityManager {

    @Autowired
    private DatabaseManager dbManager;


    public <T extends Entity> void delete(T entity)
            throws EntityManagerException {
        if (entity.getId() == 0)
            throw new EntityManagerException("Entity id must be specified");
        dbManager.delete(entity.getId());
    }


    // TODO : add nullability checks
    // TODO : fix circular stackOverflow
    public <T extends Entity> T load(long id, Class<T> clazz)
            throws Exception {
        T entity = clazz.newInstance();
        entity.setId(id);

        // TODO : dbm update(...)
        loadParams(clazz, entity);
        loadRefs(clazz, entity);
        return entity;
    }


    public <T extends Entity> void save(T entity)
            throws EntityManagerException {
        if (entity.getId() == 0)
            throw new EntityManagerException("Entity id must be specified");

        dbManager.deleteReferences(entity.getId());
        dbManager.deleteValues(entity.getId());

        try {
            saveParams(entity);
            saveRefs(entity);
        } catch (Exception e) {
            throw new EntityManagerException(e.getMessage(), e .getCause());
        }
    }


    // Предполанается уникальность пары (object_id, attr_id) в Params
    private <T extends Entity> void loadParams(Class<T> clazz, T entity)
            throws Exception {
        Map<Long, Field> paramFields = Reflection.getParamFields(clazz);

        for (Map<String, Object> param : dbManager.getValues(entity.getId())) {
            Field paramField = paramFields.get(param.get("attr_id"));
            if (paramField != null)
                Reflection.setField(paramField, entity, param.get("value"));
        }
    }


    private <T extends Entity> void loadRefs(Class<T> clazz, T entity)
            throws Exception {

        Map<Long, Field> refFields = Reflection.getRefFields(clazz);

        for (Iterator<Map.Entry<Long, Field>> iter = refFields.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry<Long, Field> fieldEntry = iter.next();
            Object reference;

            if (fieldEntry.getValue().getType() == List.class) {
                List<Long> refs = dbManager.getReferences(entity.getId(), fieldEntry.getKey());
                ParameterizedType listType = (ParameterizedType) fieldEntry.getValue().getGenericType();
                Class<T> refClass = (Class<T>) listType.getActualTypeArguments()[0];
                reference = new ArrayList<>();

                for (Long ref : refs) {
                    ((ArrayList) reference).add(load(ref, refClass));
                }
            } else {
                Long ref = dbManager.getReference(entity.getId(), fieldEntry.getKey());
                Class<T> refClass = (Class<T>) fieldEntry.getValue().getType();
                reference = load(ref, refClass);
            }

            Reflection.setField(fieldEntry.getValue(), entity, reference);
        }
    }


    private <T extends Entity> void saveParams(T entity)
            throws IllegalAccessException, DatabaseManagerException {
        Map<Long, Field> paramFields = Reflection.getParamFields(entity.getClass());
        Map<Long, String> params = new HashMap<>();

        for (Iterator<Map.Entry<Long, Field>> iter = paramFields.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry<Long, Field> fieldEntry = iter.next();
            String value = fieldEntry.getValue().getType() == Date.class ?
                           Reflection.dateFormat.format(Reflection.getFieldValue(fieldEntry.getValue(), entity)) :
                           Reflection.getFieldValue(fieldEntry.getValue(), entity).toString();

            params.put(fieldEntry.getKey(), value);
        }

        if (params.size() > 0)
            dbManager.setValues(entity.getId(), params);
    }


    private <T extends Entity> void saveRefs(T entity)
            throws IllegalAccessException, DatabaseManagerException {
        Map<Long, Field> refFields = Reflection.getRefFields(entity.getClass());
        Map<Long, Long> refs = new HashMap<>();

        for (Iterator<Map.Entry<Long, Field>> iter = refFields.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry<Long, Field> fieldEntry = iter.next();
            if (fieldEntry.getValue().getType() == List.class) {
                for (Object ref : (List<Object>) Reflection.getFieldValue(fieldEntry.getValue(), entity))
                    refs.put(fieldEntry.getKey(), ((Entity) ref).getId());
            } else {
                refs.put(fieldEntry.getKey(),
                        ((Entity) Reflection.getFieldValue(fieldEntry.getValue(), entity)).getId());
            }
        }

        /*
        if (refs.size() > 0)
            dbManager.setReferences(entity.getId(), refs);*/
    }

}
