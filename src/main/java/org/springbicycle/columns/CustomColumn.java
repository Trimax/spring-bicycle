package org.springbicycle.columns;

import org.springbicycle.exceptions.CustomException;
import org.springbicycle.tables.AbstractTable;

public final class CustomColumn implements AbstractColumn {
    private final AbstractColumn column;
    private final AbstractTable table;
    private final String parameter;

    public CustomColumn(final AbstractTable table, final String parameter, final AbstractColumn column) {
        this.table = table;
        this.parameter = parameter;
        this.column = column;
    }

    @Override
    public final AbstractTable getTable() {
        return table;
    }

    @Override
    public final AbstractColumn getCorrespondingForeignColumn() {
        return null;
    }

    @Override
    public Class<?> getType() {
        throw new CustomException("This method should not be called from this class");
    }

    @Override
    public final String getColumnName() {
        return column.getColumnName();
    }

    @Override
    public final String toString() {
        return parameter;
    }
}
