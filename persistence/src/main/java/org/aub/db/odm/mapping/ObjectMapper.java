package org.aub.db.odm.mapping;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.aub.db.odm.annotation.Column;
import org.aub.db.odm.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;

//TODO Move to other project
public class ObjectMapper {

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
