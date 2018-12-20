package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.annotation.*;
import com.netcreker.meetup.databasemanager.DatabaseManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.netcreker.meetup.util.EntityManagerUtil.*;

@Component
public class EntityManagerImpl implements EntityManager {

    @Autowired
    private DatabaseManager dbManager;

    // TODO : ADD REFERENCES
    // TODO : add nullability checks
    public <T> T load(long id, Class<T> clazz)
            throws IllegalAccessException, InstantiationException, EntityManagerException {
        /*
        List<Field> idFields = getFieldsWithAnnotation(clazz, Id.class);

        if (idFields.size() != 1)
            throw new EntityManagerException(
                "Unable to resolve @Id annotation; encountered zero or multiple @Ids");

        List<String> parameterAttrNames = getParameterAttrNames(clazz);
        Map<String, String> parameters = dbManager.getParameters(id,
                parameterAttrNames);

        T model = clazz.newInstance();
        setParameters(model, clazz, parameters);
        setFieldValue(model, idFields.get(0), id);
        return model;
        */
        throw new NotImplementedException();
    }

    public <T extends Object> void save(T instance)
            throws NotImplementedException {
        //TODO : implement saving logic
        throw new NotImplementedException();
    }

    public void delete(long id) {
        dbManager.delete(id);
    }

}
