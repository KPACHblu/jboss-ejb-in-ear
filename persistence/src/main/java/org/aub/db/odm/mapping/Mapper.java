package org.aub.db.odm.mapping;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.commons.lang3.StringUtils;
import org.aub.db.domain.Advert;
import org.aub.db.odm.annotation.Column;
import org.aub.db.odm.annotation.Table;
import org.aub.db.odm.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;

//TODO Move to other project
public class Mapper {

    public static DBObject toDbObject(Object object) {
        DBObject result = new BasicDBObject();
        Class objectClass = object.getClass();

        List<Field> fields = ReflectionUtils.getAllValidFields(objectClass);
        for (Field field : fields) {
            addFieldToDBObject(object, field, result);
        }
        return result;
    }

    public static <T> T toEntity(DBObject dbObject, Class<T> objectClass) {
        T object = ReflectionUtils.getNewInstance(objectClass);

        List<Field> fields = ReflectionUtils.getAllValidFields(objectClass);
        for (Field field : fields) {
            setFieldValue(field, object, dbObject);
        }
        return object;
    }

    public static <T> String getEntityTableName(Class<T> objectClass) {
        String tableName;
        Table tableAnnotation = objectClass.getAnnotation(Table.class);
        if (tableAnnotation != null && StringUtils.isNotBlank(tableAnnotation.name())) {
            tableName = tableAnnotation.name();
        } else {
            tableName = objectClass.getSimpleName();
        }
        return tableName;
    }

    private static void setFieldValue(Field field, Object object, DBObject dbObject) {
        if (dbObject.containsField(field.getName())) {
            ReflectionUtils.setFieldValue(field, object, dbObject.get(field.getName()));
        } else {
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.name();
                if (columnName != null) {
                    if (dbObject.containsField(columnName)) {
                        ReflectionUtils.setFieldValue(field, object, dbObject.get(columnName));
                    }
                }
            }
        }
    }

    private static void addFieldToDBObject(Object object, Field field, DBObject dbObject) {
        field.setAccessible(true);
        Object fieldValue = ReflectionUtils.getFieldValue(field, object);
        Column columnAnnotation = field.getAnnotation(Column.class);
        if (columnAnnotation != null) {
            String columnName = columnAnnotation.name();
            if (columnName == null) {
                columnName = field.getName();
            }
            dbObject.put(columnName, fieldValue);
        } else {
            dbObject.put(field.getName(), fieldValue);
        }
    }
}
