package org.springbicycle.queries.logicalexpressions;

import org.springbicycle.columns.AbstractColumn;
import org.springbicycle.queries.stringexpressions.AbstractStringExpression;

public final class LikeExpression extends AbstractLogicalExpression {
    private final AbstractStringExpression expression;
    private final AbstractColumn column;

    public LikeExpression(final AbstractColumn column, final AbstractStringExpression expression) {
        this.expression = expression;
        this.column     = column;
    }

    @Override
    public final String build() {
        return "(" + column.build() + " LIKE " + expression.build() + ")";
    }
}
