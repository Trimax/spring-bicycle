package org.springbicycle.queries.logicalexpressions;

public final class NotExpression extends AbstractLogicalExpression {
    private final AbstractLogicalExpression expression;

    public NotExpression(final AbstractLogicalExpression expression) {
        this.expression = expression;
    }

    @Override
    public final String build() {
        return "(NOT " + expression.build() + ")";
    }
}
