package com.netcreker.meetup.util;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Reflection {

    public static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static Class<?> getActualClass(Field field) {
        Class<?> clazz = field.getType();
        if (clazz == List.class) {
            ParameterizedType listType = (ParameterizedType) field.getGenericType();
            clazz = (Class<?>) listType.getActualTypeArguments()[0];
        }
        return clazz;
    }

    public static Map<Long, Field> getParamFields(Class<?> clazz) {
        Map<Long, Field> paramFields = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Parameter.class)) {
                paramFields.put(
                        field.getAnnotation(Parameter.class).value(),
                        field);
            }
        }
        return paramFields;
    }

    public static Map<Long, Field> getRefFields(Class<?> clazz) {
        Map<Long, Field> refFields = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Reference.class)) {
                refFields.put(
                        field.getAnnotation(Reference.class).value(),
                        field);
            }
        }
        return refFields;
    }

    public static List<Long> getReference(Field field, Object instance) throws IllegalAccessException {
        boolean accessible = field.isAccessible();
        try {
            field.setAccessible(true);
            Object fieldValue = field.get(instance);
            List<Long> result = new ArrayList<>();

            if (fieldValue != null) {
                if (field.getType() == List.class) {
                    ((List<Entity>) fieldValue).forEach(x -> result.add(x.getId()));
                } else {
                    result.add(((Entity) fieldValue).getId());
                }
            }

            return result;
        } finally {
            field.setAccessible(accessible);
        }
    }

    public static List<String> getValue(Field field, Object instance) throws IllegalAccessException {
        boolean accessible = field.isAccessible();
        try {
            field.setAccessible(true);
            Object fieldValue = field.get(instance);
            List<String> result = new ArrayList<>();

            if (fieldValue != null) {
                if (field.getType() == List.class) {
                    ((List<Object>) fieldValue).forEach(x -> result.add(x.toString()));
                } else {
                    String value = field.getType() == Date.class ?
                            dateFormat.format(fieldValue) :
                            fieldValue.toString();
                    result.add(value);
                }
            }

            return result;
        } finally {
            field.setAccessible(accessible);
        }
    }

    public static long getObjectType(Class<?> clazz) throws ReflectiveOperationException {
        if (!clazz.isAnnotationPresent(ObjectType.class))
            throw new ReflectiveOperationException("ObjectType annotation is absent in class " + clazz.getName());
        return clazz.getDeclaredAnnotation(ObjectType.class).value();
    }

    public static void setField(Field field, Object instance, Object value)
            throws IllegalAccessException, ParseException {
        boolean accessible = field.isAccessible();
        field.setAccessible(true);

        if (field.getType() == List.class) {
            ParameterizedType listType = (ParameterizedType) field.getGenericType();
            Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];

            List<Object> actualList = (List<Object>) field.get(instance);
            if (actualList == null)
                actualList = new ArrayList<>();

            actualList.add(listClass.cast(value));
            field.set(instance, actualList);
        }
        else {
            if (field.getType() == Date.class) {
                field.set(instance, dateFormat.parse(value.toString()));
            } else {
                field.set(instance, field.getType().cast(value));
            }
        }

        field.setAccessible(accessible);
    }

}
