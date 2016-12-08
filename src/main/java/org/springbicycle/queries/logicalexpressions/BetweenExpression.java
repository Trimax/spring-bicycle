package org.springbicycle.queries.logicalexpressions;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.queries.stringexpressions.AbstractStringExpression;

public final class BetweenExpression extends AbstractLogicalExpression {
    private final AbstractStringExpression parameterMin;
    private final AbstractStringExpression parameterMax;
    private final AbstractColumn column;

    public BetweenExpression(final AbstractColumn column, final AbstractStringExpression parameterMin, final AbstractStringExpression parameterMax) {
        this.parameterMin = parameterMin;
        this.parameterMax = parameterMax;
        this.column       = column;
    }

    @Override
    public final String build() {
        return "(" + column.getColumnNameWithTableName() + " BETWEEN " + parameterMin.build() + " AND " + parameterMax.build() + ")";
    }
}
