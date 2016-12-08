package org.springbicycle.tables;

import org.apache.commons.lang3.StringUtils;
import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.exceptions.CustomException;

import java.util.List;

public interface AbstractTable {
    List<AbstractColumn> getColumns();
    AbstractColumn getPrimaryColumn();
    String getName();

    default AbstractColumn getColumn(final String columnName){
        return getColumns().stream().filter(column -> StringUtils.equals(column.getColumnName(), columnName)).findAny().orElse(null);
    }

    default AbstractColumn ALL() {
        return new AbstractColumn() {
            @Override
            public final AbstractTable getTable() {
                return AbstractTable.this;
            }

            @Override
            public final Class<?> getType() {
                throw new CustomException("Method can't be executed for this type of column (ALL)");
            }

            @Override
            public final AbstractColumn getCorrespondingForeignColumn() {
                throw new CustomException("Method can't be executed for this type of column (ALL)");
            }

            @Override
            public final String getColumnName() {
                return "*";
            }
        };
    }
}
