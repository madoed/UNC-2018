package com.netcreker.meetup.util;

import com.netcreker.meetup.annotations.Parameter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class EntityManagerUtil {
    public static List<Field> getFieldsWithAnnotation(Class<?> clazz, Class<? extends Annotation> ann) {
        List<Field> fieldList = new LinkedList<>();
        Class<?> c = clazz;
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(ann)) {
                    fieldList.add(field);
                }
            }
            c = c.getSuperclass();
        }
        return fieldList;
    }

    public static List<String> getParameterAttrNames(Class<?> clazz) {
        List<String> parameters = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Parameter.class)) {
                parameters.add(field.getAnnotation(Parameter.class).name());
            }
        }
        return parameters;
    }

    // TODO : decorator method for set & get field value
    public static <T> void setFieldValue(Object instance, Field field, T value) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(instance, value);
            field.setAccessible(accessible);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T> T getFieldValue(Object instance, Field field) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Object value = field.get(instance);
            field.setAccessible(accessible);
            return (T) value;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setParameters(Object instance,
              Class<?> clazz, Map<String, String> parameters) {
        List<Field> parameterFields = getFieldsWithAnnotation(
                clazz, Parameter.class);
        for (Field field : parameterFields) {
            String value = parameters.get(field.getAnnotation(
                    Parameter.class).name());
            if (value == null) continue;

            // TODO : connect this to Attributes.attr_type in DB
            // TODO : exception handling
            if (field.getType() == int.class){
                setFieldValue(instance, field, Integer.parseInt(value));
            } else if (field.getType() == byte.class){
                setFieldValue(instance, field, Byte.parseByte(value));
            } else {
                setFieldValue(instance, field, value);
            }
        }
    }

    public static <T> Map<String, T> getParameterValues(Object instance, Class<?> clazz) {
        Map<String, T> values = new HashMap<>();
        List<Field> parameters = getFieldsWithAnnotation(clazz, Parameter.class);
        for (Field field : parameters) {
            values.put(field.getAnnotation(Parameter.class).name(),
                    getFieldValue(instance, field));
        }
        return values;
    }
}
