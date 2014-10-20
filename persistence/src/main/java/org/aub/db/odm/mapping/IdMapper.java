package org.aub.db.odm.mapping;

import org.apache.commons.lang3.StringUtils;
import org.aub.db.odm.annotation.Column;
import org.aub.db.odm.annotation.Id;
import org.aub.db.odm.util.ReflectionUtils;

import java.lang.reflect.Field;

public class IdMapper {

    public static <T> String getEntityIdFieldName(Class<T> objectClass) {
        String idFieldName = null;
        for (Field field : objectClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation != null) {
                        String columnName = columnAnnotation.name();
                        idFieldName = StringUtils.isBlank(columnName) ? field.getName() : columnName;
                    }
                } else {
                    idFieldName = field.getName();
                }
            }
        }
        return idFieldName;
    }

    public static Object getEntityIdFieldValue(Object object) {
        Object idFieldValue = null;
        Class<? extends Object> objectClass = object.getClass();
        for (Field field : objectClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                idFieldValue = ReflectionUtils.getFieldValue(field, object);
            }
        }
        return idFieldValue;
    }
}
