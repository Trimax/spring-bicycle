package org.springbicycle.columns;

import org.springbicycle.queries.functions.Selectable;
import org.springbicycle.tables.AbstractTable;

public interface AbstractColumn extends Selectable {
    default String getColumnNameWithTableName() {
        return getTable().getName() + "." + getColumnName();
    }

    @Override
    default String build() {
        return getColumnNameWithTableName();
    }

    AbstractTable getTable();
    AbstractColumn getCorrespondingForeignColumn();
    Class<?> getType();
    String getColumnName();
}
