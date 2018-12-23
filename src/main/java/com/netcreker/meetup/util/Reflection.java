package com.netcreker.meetup.util;

import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Reflection {

    public static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public static List<Field> getFieldsWithAnnotation(
            Class<?> clazz, Class<? extends Annotation> ann, boolean recursive) {
        List<Field> fieldList = new ArrayList<>();
        Class<?> c = clazz;

        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(ann)) {
                    fieldList.add(field);
                }
            }
            c = recursive ? c.getSuperclass() : null;
        }

        return fieldList;
    }

    public static Map<Long, Field> getParamFields(Class<?> clazz) {
        Map<Long, Field> paramFields = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Parameter.class)) {
                paramFields.put(
                        field.getAnnotation(Parameter.class).attrId(),
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
                        field.getAnnotation(Reference.class).attrId(),
                        field);
            }
        }
        return refFields;
    }

    public static <T> void setField(Field field, Object instance, T value)
        throws IllegalAccessException, ParseException {

        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        /*
        if (field.getType() == List.class) {
            ParameterizedType listType = (ParameterizedType) field.getGenericType();
            Class<?> listClass = (Class<?>) listType.getActualTypeArguments()[0];

            List<Object> actualList = (List<Object>)field.get(instance);
            if (actualList == null)
                actualList = new ArrayList<>();

            actualList.add(listClass.cast(value));
            field.set(instance, actualList);
        }
        else {
            field.set(instance, field.getType().cast(value));
        }
        */
        if (field.getType() == Date.class) {
            field.set(instance, dateFormat.parse(value.toString()));
        } else {
            field.set(instance, field.getType().cast(value));
        }
        field.setAccessible(accessible);
    }

    public static <T> Object getFieldValue(Field field, T instance)
            throws IllegalAccessException {
        boolean accessible = field.isAccessible();
        try {
            field.setAccessible(true);
            return field.get(instance);
        } finally {
            field.setAccessible(accessible);
        }
    }

}
