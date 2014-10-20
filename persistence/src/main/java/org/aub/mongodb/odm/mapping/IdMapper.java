package org.aub.mongodb.odm.mapping;

import org.apache.commons.lang3.StringUtils;
import org.aub.mongodb.odm.annotation.Column;
import org.aub.mongodb.odm.annotation.Id;
import org.aub.mongodb.odm.util.ReflectionUtils;

import java.lang.reflect.Field;

public class IdMapper {

    public static <T> String getEntityIdFieldName(Class<T> objectClass) {
        String idFieldName = null;
        for (Field field : ReflectionUtils.getAllValidFields(objectClass)) {
            if (field.isAnnotationPresent(Id.class)) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation != null) {
                        String columnName = columnAnnotation.name();
                        idFieldName = StringUtils.isBlank(columnName) ? field.getName() : columnName;
                        break;
                    }
                } else {
                    idFieldName = field.getName();
                    break;
                }
            }
        }
        return idFieldName;
    }

    public static Object getEntityIdFieldValue(Object object) {
        Object idFieldValue = null;
        Class<? extends Object> objectClass = object.getClass();
        for (Field field : ReflectionUtils.getAllValidFields(objectClass)) {
            if (field.isAnnotationPresent(Id.class)) {
                idFieldValue = ReflectionUtils.getFieldValue(field, object);
                break;
            }
        }
        return idFieldValue;
    }
}
