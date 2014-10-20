package org.aub.db.odm.mapping;

import org.apache.commons.lang3.StringUtils;
import org.aub.db.odm.annotation.Table;

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
