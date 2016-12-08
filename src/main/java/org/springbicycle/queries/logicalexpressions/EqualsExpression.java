package org.springbicycle.queries.logicalexpressions;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.queries.stringexpressions.AbstractStringExpression;

public final class EqualsExpression extends AbstractLogicalExpression {
    private final AbstractStringExpression parameter;
    private final AbstractColumn column;

    public EqualsExpression(final AbstractColumn column, final AbstractStringExpression parameter) {
        this.parameter = parameter;
        this.column    = column;
    }

    @Override
    public final String build() {
        return "(" + column.getColumnNameWithTableName() + " = " + parameter.build() + ")";
    }
}
