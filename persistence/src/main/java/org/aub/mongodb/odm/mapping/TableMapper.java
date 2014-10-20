package org.aub.mongodb.odm.mapping;

import org.apache.commons.lang3.StringUtils;
import org.aub.mongodb.odm.annotation.Table;

public class TableMapper {

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
}
