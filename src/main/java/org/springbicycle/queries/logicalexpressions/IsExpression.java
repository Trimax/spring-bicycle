package org.springbicycle.queries.logicalexpressions;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.queries.stringexpressions.AbstractStringExpression;

public final class IsExpression extends AbstractLogicalExpression {
    private final AbstractStringExpression parameter;
    private final AbstractColumn column;

    public IsExpression(final AbstractColumn column, final AbstractStringExpression parameter) {
        this.parameter = parameter;
        this.column    = column;
    }

    @Override
    public final String build() {
        return "(" + column.getColumnNameWithTableName() + " IS " + parameter.build() + ")";
    }
}
