package org.springbicycle.queries.logicalexpressions;

import org.springbicycle.columns.AbstractColumn;

public class ColumnEqualsExpression extends AbstractLogicalExpression {
    private final AbstractColumn column1;
    private final AbstractColumn column2;

    public ColumnEqualsExpression(final AbstractColumn column1, final AbstractColumn column2) {
        this.column1 = column1;
        this.column2 = column2;
    }

    @Override
    public final String build() {
        return "(" + column1.getColumnNameWithTableName() + " = " + column2.getColumnNameWithTableName() + ")";
    }
}
