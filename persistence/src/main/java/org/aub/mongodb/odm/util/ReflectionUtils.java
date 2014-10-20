package org.aub.mongodb.odm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {

    public static List<Field> getAllValidFields(Class clazz) {
        List<Field> result = new ArrayList<>();
        return getAllValidFieldsRec(clazz, result);
    }

    private static List<Field> getAllValidFieldsRec(Class clazz, List<Field> result) {
        Class superClazz = clazz.getSuperclass();
        if (superClazz != null) {
            getAllValidFieldsRec(superClazz, result);
        }
        for (Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
                result.add(field);
            }
        }
        return result;
    }

    public static <T> T getNewInstance(Class<T> objectClass) {
        try {
            return objectClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setFieldValue(Field field, Object object, Object value) {
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getFieldValue(Field field, Object object) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
