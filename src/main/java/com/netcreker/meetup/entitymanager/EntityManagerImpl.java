package com.netcreker.meetup.entitymanager;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Cache;
import com.netcreker.meetup.databasemanager.DatabaseManager;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.util.Reflection;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class EntityManagerImpl implements EntityManager {

    private final DatabaseManager dbManager;

    private final Cache<Long, Entity> entities = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(5, TimeUnit.MINUTES)
            //.refreshAfterWrite(5, TimeUnit.MINUTES)
            .build();

    @Autowired
    public EntityManagerImpl(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }


    @Override
    public <T extends Entity> void delete(@NonNull T entity) {
        dbManager.delete(entity.getId());
        entities.invalidate(entity.getId());
    }

    @Override
    public <T extends Entity> T load(@NonNull Class<T> clazz, long id) {
        T entity = (T) entities.getIfPresent(id);
        if (entity == null) {
            try {
                entity = clazz.newInstance();
                entity.setId(id);
                entity.setName(dbManager.getName(id));

                loadParams(clazz, entity);
                entities.put(id, entity);
                loadRefs(clazz, entity);
            } catch (InstantiationException | IllegalAccessException | ParseException e) {
                throw new EntityManagerException(e.getMessage(), e);
            }
        }
        return entity;
    }

    @Override
    public <T extends Entity> void save(@NonNull T entity) {
        try {
            if (entity.getId() == 0) {
                entity.setId(dbManager.create(
                        Reflection.getObjectType(entity.getClass()),
                        entity.getName()));
            }
            dbManager.update(entity.getId(), getParams(entity), getRefs(entity));
            entities.put(entity.getId(), entity);
        } catch (ReflectiveOperationException e) {
            throw new EntityManagerException(e.getMessage(), e);
        }
    }

    private <T extends Entity> void loadParams(Class<T> clazz, Entity entity)
            throws IllegalAccessException, ParseException {
        Map<Long, Field> paramFields = Reflection.getParamFields(clazz);

        for (Map<String, Object> param : dbManager.getAllValues(entity.getId())) {
            Field field = paramFields.get(param.get("attr_id"));
            if (field != null)
                Reflection.setField(field, entity, param.get("value"));
        }
    }

    private <T extends Entity> void loadRefs(Class<T> clazz, Entity entity)
            throws IllegalAccessException, ParseException {
        Map<Long, Field> refFields = Reflection.getRefFields(clazz);

        for (Map<String, Object> ref : dbManager.getAllReferences(entity.getId())) {
            Field field = refFields.get(ref.get("attr_id"));
            if (field != null) {
                Class<? extends Entity> refClass = (Class<? extends Entity>) Reflection.getActualClass(field);
                long refId = Long.parseLong(ref.get("reference").toString());
                Reflection.setField(field, entity, load(refClass, refId));
            }
        }
    }

    private List<Map<Long, String>>getParams(Entity entity) throws IllegalAccessException {
        Map<Long, Field> paramFields = Reflection.getParamFields(entity.getClass());
        List<Map<Long, String>> params = new ArrayList<>();

        for (Map.Entry<Long, Field> fieldEntry : paramFields.entrySet()) {
            List<String> values = Reflection.getValue(fieldEntry.getValue(), entity);
            for (String value : values) {
                Map<Long, String> map = new HashMap<>();
                map.put(fieldEntry.getKey(), value);
                params.add(map);
            }
        }

        return params;
    }

    private List<Map<Long, Long>> getRefs(Entity entity) throws IllegalAccessException {
        Map<Long, Field> refFields = Reflection.getRefFields(entity.getClass());
        List<Map<Long, Long>> references = new ArrayList<>();

        for (Map.Entry<Long, Field> fieldEntry : refFields.entrySet()) {
            List<Long> refs = Reflection.getReference(fieldEntry.getValue(), entity);
            for (long ref : refs) {
                Map<Long, Long> map = new HashMap<>();
                map.put(fieldEntry.getKey(), ref);
                references.add(map);
            }
        }

        return references;
    }

}
